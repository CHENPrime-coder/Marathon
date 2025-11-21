package com.example.marathon;

import com.example.marathon.security.TokenService;
import com.example.marathon.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MarathonApplicationTests {

    @Autowired
    private TokenService tokenService;

    @Test
    void contextLoads() {
        String result = tokenService.generateToken("aaa@aaa.com");
        System.out.println("Generated Token: " + result);
    }

}
