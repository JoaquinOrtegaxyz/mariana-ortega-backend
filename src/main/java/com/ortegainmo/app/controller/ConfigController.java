package com.ortegainmo.app.controller;

import com.ortegainmo.app.dto.config.SiteConfigDTO;
import com.ortegainmo.app.model.SiteConfig;
import com.ortegainmo.app.repository.SiteConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
@RequiredArgsConstructor
public class ConfigController {

    private final SiteConfigRepository configRepository;

    @GetMapping
    @Cacheable(value = "siteConfig", key = "'singleton'")
    public ResponseEntity<SiteConfigDTO> getConfig() {
        SiteConfig config = configRepository.findById(1L).orElseGet(() -> {
            SiteConfig newConfig = SiteConfig.builder()
                    .whatsapp("5492262579622")
                    .instagram("")
                    .facebook("")
                    .build();
            return configRepository.save(newConfig);
        });

        return ResponseEntity.ok(new SiteConfigDTO(config.getWhatsapp(), config.getInstagram(), config.getFacebook()));
    }

    @PutMapping
    @CacheEvict(value = "siteConfig", allEntries = true)
    public ResponseEntity<SiteConfigDTO> updateConfig(@RequestBody SiteConfigDTO dto) {
        SiteConfig config = configRepository.findById(1L).orElseGet(() -> new SiteConfig());
        config.setWhatsapp(dto.whatsapp());
        config.setInstagram(dto.instagram());
        config.setFacebook(dto.facebook());
        configRepository.save(config);

        return ResponseEntity.ok(dto);
    }
}