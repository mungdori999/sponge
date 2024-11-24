package com.petweb.sponge.jwt;

import com.amazonaws.HttpMethod;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petweb.sponge.exception.ResponseError;
import com.petweb.sponge.exception.error.NotFoundToken;
import com.petweb.sponge.oauth2.dto.CustomOAuth2User;
import com.petweb.sponge.oauth2.dto.LoginAuth;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

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

        String token = request.getHeader("Authorization");

        if (token == null && !request.getMethod().equalsIgnoreCase("GET")) {
            response.setStatus(401);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try {
                String json = new ObjectMapper().writeValueAsString(new ResponseError(401, "토큰이 없습니다."));
                response.getWriter().write(json);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            return;
        }

        if (token != null) {

            //토큰 소멸 시간 검증
            try {
                jwtUtil.isExpired(token);

            } catch (ExpiredJwtException jwtException) {
                response.setStatus(401);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                try {
                    String json = new ObjectMapper().writeValueAsString(new ResponseError(401, "토큰이 만료되었습니다."));
                    response.getWriter().write(json);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
                return;
            } catch (SignatureException signatureException) {
                response.setStatus(401);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                try {
                    String json = new ObjectMapper().writeValueAsString(new ResponseError(401, "위조된 토큰입니다."));
                    response.getWriter().write(json);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
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
}
