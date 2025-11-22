package com.example.marathon.controller;

import com.example.marathon.api.ApiResponse;
import com.example.marathon.dto.registration.RegistrationRequest;
import com.example.marathon.dto.registration.RegistrationResponse;
import com.example.marathon.security.AuthContext;
import com.example.marathon.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ApiResponse<RegistrationResponse> register(@RequestBody @Valid RegistrationRequest request) {
        return ApiResponse.success(registrationService.register(request, AuthContext.getEmail()));
    }
}
