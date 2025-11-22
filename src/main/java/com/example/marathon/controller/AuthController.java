package com.example.marathon.controller;

import com.example.marathon.api.ApiResponse;
import com.example.marathon.dto.auth.LoginRequest;
import com.example.marathon.dto.auth.LoginResponse;
import com.example.marathon.dto.auth.UpdatePasswordRequest;
import com.example.marathon.security.AuthContext;
import com.example.marathon.security.TokenService;
import com.example.marathon.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ApiResponse.success(authService.login(request));
    }

    @PostMapping("/password")
    public ApiResponse<Void> updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        authService.updatePassword(AuthContext.getEmail(), request);
        return ApiResponse.success();
    }
}
