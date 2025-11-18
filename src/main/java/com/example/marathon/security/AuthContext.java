package com.example.marathon.security;

public class AuthContext {
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
