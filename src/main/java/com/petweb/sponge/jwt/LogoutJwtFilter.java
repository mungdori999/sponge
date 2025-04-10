package com.petweb.sponge.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petweb.sponge.exception.ResponseError;
import com.petweb.sponge.utils.ResponseHttpStatus;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;


import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
public class LogoutJwtFilter extends GenericFilterBean {
    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String requestUri = request.getRequestURI();

        if (!requestUri.equals("/api/logout")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!request.getMethod().equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");

        // 토큰이 없을때
        if (token == null && !request.getMethod().equalsIgnoreCase("GET")) {
            settingResponse(new ResponseError(401, "토큰이 없습니다."), response);
        }
        //토큰 소멸 시간 검증
        try {
            jwtUtil.isExpired(token);
        } catch (ExpiredJwtException jwtException) {
            settingResponse(new ResponseError(ResponseHttpStatus.EXPIRE_ACCESS_TOKEN.getCode(), "토큰이 만료되었습니다."), response);
        } catch (SignatureException signatureException) {
            settingResponse(new ResponseError(401, "위조된 토큰입니다."), response);
        }
        String body = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        RefreshToken tokenDto = objectMapper.readValue(body, RefreshToken.class);
        String refreshToken = tokenDto.getRefreshToken();
        refreshRepository.deleteByRefresh(refreshToken);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
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
