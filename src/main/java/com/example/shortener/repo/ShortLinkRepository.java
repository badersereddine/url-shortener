package com.example.shortener.repo;

import com.example.shortener.entity.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {
    Optional<ShortLink> findByCode(String code);
    boolean existsByCode(String code);
}
