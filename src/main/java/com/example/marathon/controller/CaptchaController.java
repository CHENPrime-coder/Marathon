package com.example.marathon.controller;

import com.example.marathon.api.ApiResponse;
import com.example.marathon.dto.captcha.CaptchaResponse;
import com.example.marathon.service.CaptchaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/captcha")
public class CaptchaController {

    private final CaptchaService captchaService;

    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @GetMapping
    public ApiResponse<CaptchaResponse> create() {
        var captcha = captchaService.generate();
        return ApiResponse.success(new CaptchaResponse(captcha.id(), captcha.imageBase64(), captcha.expiresInSeconds()));
    }
}
