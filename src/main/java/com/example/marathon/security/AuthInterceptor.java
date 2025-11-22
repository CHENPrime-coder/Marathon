package com.example.marathon.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    // 公共接口：验证码、登录、注册跑者、基础数据查询
    private static final List<String> WHITE_LIST = List.of(
            "/api/captcha",
            "/api/auth/login",
            "/api/runners",
            "/api/competitions",
            "/api/race-kit-options",
            "/api/cities",
            "/api/genders",
            "/api/experience-levels",
            "/api/events",
            "/swagger",
            "/v3/api-docs"
    );
    private final TokenService tokenService;

    public AuthInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getRequestURI();
        String method = request.getMethod();

        // 放行白名单和预检请求(CORS)
        if (WHITE_LIST.contains(path) || path.startsWith("/files/") || method.equalsIgnoreCase("OPTIONS")) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(401);
            return false;
        }
        String token = authHeader.substring(7);
        String email = tokenService.parseEmail(token).orElse(null);
        if (email == null) {
            response.setStatus(401);
            return false;
        }
        AuthContext.setEmail(email);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {
        AuthContext.clear();
    }
}
