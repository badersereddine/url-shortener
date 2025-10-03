package com.example.shortener.api;

import com.example.shortener.api.dto.CreateLinkRequest;
import com.example.shortener.api.dto.CreateLinkResponse;
import com.example.shortener.entity.ShortLink;
import com.example.shortener.service.ShortLinkService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping
public class ShortLinkController {
    private final ShortLinkService service;
    @Value("${app.base-url}") private String baseUrl;

    public ShortLinkController(ShortLinkService service) { this.service = service; }

    // POST /api/v1/links -> create short URL
    @PostMapping("/api/v1/links")
    public CreateLinkResponse create(@Valid @RequestBody CreateLinkRequest body) {
        ShortLink sl = service.create(body.getUrl());
        String shortUrl = baseUrl + "/r/" + sl.getCode();
        return new CreateLinkResponse(sl.getCode(), shortUrl, sl.getOriginalUrl());
    }

    // GET /r/{code} -> redirect 302 at the original URL
    @GetMapping("/r/{code}")
    public void redirect(@PathVariable String code, HttpServletResponse response) throws IOException {
        ShortLink sl = service.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Code not found"));
        service.incrementHit(sl);
        response.setStatus(HttpServletResponse.SC_FOUND);
        response.setHeader("Location", sl.getOriginalUrl());
    }

    // GET /api/v1/links/{code} -> details (usefull for the test)
    @GetMapping("/api/v1/links/{code}")
    public CreateLinkResponse get(@PathVariable String code) {
        ShortLink sl = service.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Code not found"));
        String shortUrl = baseUrl + "/r/" + sl.getCode();
        return new CreateLinkResponse(sl.getCode(), shortUrl, sl.getOriginalUrl());
    }
}
