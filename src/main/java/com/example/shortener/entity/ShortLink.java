package com.example.shortener.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "short_links", indexes = {
        @Index(name = "ux_code", columnList = "code", unique = true)
})
public class ShortLink {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2048)
    private String originalUrl;

    @Column(nullable = false, unique = true, length = 16)
    private String code;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private long hitCount = 0;

    public Long getId() { return id; }
    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public Instant getCreatedAt() { return createdAt; }
    public long getHitCount() { return hitCount; }
    public void setHitCount(long hitCount) { this.hitCount = hitCount; }
}
