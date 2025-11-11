package com.example.marathon.controller;

import com.example.marathon.dto.LoginRequest;
import com.example.marathon.pojo.Result;
import com.example.marathon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/access")
class AccessController {

    UserService userService;

    @Autowired
    public AccessController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginRequest request) {
        boolean result = userService.loginWithEmail(request.email(), request.password());
        if (result) return Result.success("success");
        else return Result.error("fail");
    }

}
