package com.example.shortener.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // GET /hello -> simple ping
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    // GET /health -> healthcheck minimal
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
