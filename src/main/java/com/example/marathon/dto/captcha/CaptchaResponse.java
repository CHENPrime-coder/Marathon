package com.example.marathon.dto.captcha;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CaptchaResponse {
    private String id;
    private String imageBase64;
    private int expiresInSeconds;
}
