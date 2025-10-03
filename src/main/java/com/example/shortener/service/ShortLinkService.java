package com.example.shortener.service;

import com.example.shortener.entity.ShortLink;
import com.example.shortener.repo.ShortLinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class ShortLinkService {
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int CODE_LENGTH = 7;
    private final SecureRandom random = new SecureRandom();
    private final ShortLinkRepository repo;

    public ShortLinkService(ShortLinkRepository repo) { this.repo = repo; }

    @Transactional
    public ShortLink create(String originalUrl) {
        String code = generateUniqueCode();
        ShortLink sl = new ShortLink();
        sl.setOriginalUrl(originalUrl);
        sl.setCode(code);
        return repo.save(sl);
    }

    @Transactional(readOnly = true)
    public Optional<ShortLink> findByCode(String code) {
        return repo.findByCode(code);
    }

    @Transactional
    public void incrementHit(ShortLink sl) {
        sl.setHitCount(sl.getHitCount() + 1);
        repo.save(sl);
    }

    private String generateUniqueCode() {
        while (true) {
            String code = randomCode();
            if (!repo.existsByCode(code)) return code;
        }
    }
    private String randomCode() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
}
