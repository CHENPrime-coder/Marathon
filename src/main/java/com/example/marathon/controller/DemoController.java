package com.example.marathon.controller;

import com.example.marathon.pojo.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
class DemoController {

    @GetMapping()
    public Result<String> get() {
        return Result.success("Hello World.");
    }

    @GetMapping("exception")
    public void exception() {
        int a = 0 / 0;
    }

}
