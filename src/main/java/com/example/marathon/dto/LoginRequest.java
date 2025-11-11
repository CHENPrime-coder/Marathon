package com.example.marathon.dto;

public record LoginRequest(
        String email,
        String password
) {}
