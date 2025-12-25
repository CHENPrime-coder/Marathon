package com.example.marathon.service;

import org.springframework.stereotype.Service;

@Service
public class ExperienceService {

    public String getExperienceLabel(String value) {
        return switch (value) {
            case "First time" -> "初次尝试";
            case "Have some experience" -> "有一些经验";
            case "Experienced" -> "经验丰富的";
            default -> "未知经验水平";
        };
    }

}
