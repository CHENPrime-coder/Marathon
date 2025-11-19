package com.example.marathon.security;

public class AuthContext {
    // 各个线程独立存储当前用户的 email 信息
    private static final ThreadLocal<String> CURRENT_EMAIL = new ThreadLocal<>();

    public static void setEmail(String email) {
        CURRENT_EMAIL.set(email);
    }

    public static String getEmail() {
        return CURRENT_EMAIL.get();
    }

    public static void clear() {
        CURRENT_EMAIL.remove();
    }
}
