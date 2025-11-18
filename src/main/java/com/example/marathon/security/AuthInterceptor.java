package com.example.marathon.security;

import com.example.marathon.exception.BizException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final TokenService tokenService;

    public AuthInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        String path = request.getRequestURI();
//        String method = request.getMethod();
//        // 公共接口：验证码、登录、注册跑者、基础数据查询
//        if ("/api/captcha".equals(path)
//                || "/api/auth/login".equals(path)
//                || ("/api/runners".equals(path) && "POST".equalsIgnoreCase(method))
//                || ("GET".equalsIgnoreCase(method) && (path.startsWith("/api/competitions")
//                || path.startsWith("/api/race-kit-options")
//                || path.startsWith("/api/cities"))
//                || path.startsWith("/swagger")
//                || path.startsWith("/v3/api-docs"))
//        ) {
//            return true;
//        }
//
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            throw new BizException(401, "unauthorized");
//        }
//        String token = authHeader.substring(7);
//        String email = tokenService.parseEmail(token).orElseThrow(() -> new BizException(401, "invalid token"));
//        AuthContext.setEmail(email);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        AuthContext.clear();
    }
}
