package com.example.shortener.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateLinkRequest {
    @NotBlank
    @Size(max = 2048)
    @Pattern(regexp = "^(https?://).+", message = "URL must start with http:// or https://")
    private String url;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
