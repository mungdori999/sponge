package com.petweb.sponge.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petweb.sponge.exception.ResponseError;
import com.petweb.sponge.oauth2.dto.CustomOAuth2User;
import com.petweb.sponge.oauth2.dto.LoginAuth;
import com.petweb.sponge.utils.ResponseHttpStatus;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();

        // /api/auth/** 경로를 필터링하지 않음
        if (requestUri.matches("^/api/auth(?:/.*)?$")) {
            filterChain.doFilter(request, response);
            return;
        }
        // /api/trainer 경로 중 POST 메서드는 필터링하지 않음
        if (requestUri.equals("/api/trainer") && request.getMethod().equalsIgnoreCase("POST")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");

        if (token == null && !request.getMethod().equalsIgnoreCase("GET")) {
            settingResponse(new ResponseError(401, "토큰이 없습니다."), response);
            return;
        }

        if (token != null) {
            //토큰 소멸 시간 검증
            try {
                jwtUtil.isExpired(token);

            } catch (ExpiredJwtException jwtException) {
                settingResponse(new ResponseError(ResponseHttpStatus.EXPIRE_ACCESS_TOKEN.getCode(), "토큰이 만료되었습니다."), response);
                return;
            } catch (SignatureException signatureException) {
                settingResponse(new ResponseError(401, "위조된 토큰입니다."), response);
                return;
            }


            //토큰에서 id 획득
            Long id = jwtUtil.getId(token);
            String loginType = jwtUtil.getLoginType(token);

            LoginAuth loginAuth = LoginAuth.builder()
                    .id(id)
                    .loginType(loginType)
                    .build();

            //UserDetails에 회원 정보 객체 담기
            CustomOAuth2User customOAuth2User = new CustomOAuth2User(loginAuth);

            //스프링 시큐리티 인증 토큰 생성
            Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null);
            //세션에 사용자 등록
            SecurityContextHolder.getContext().setAuthentication(authToken);

            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }


    private void settingResponse(ResponseError error, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(error);
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
