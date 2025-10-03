package com.example.shortener.api.dto;

public class CreateLinkResponse {
    private String code;
    private String shortUrl;
    private String originalUrl;

    public CreateLinkResponse(String code, String shortUrl, String originalUrl) {
        this.code = code; this.shortUrl = shortUrl; this.originalUrl = originalUrl;
    }
    public String getCode() { return code; }
    public String getShortUrl() { return shortUrl; }
    public String getOriginalUrl() { return originalUrl; }
}
