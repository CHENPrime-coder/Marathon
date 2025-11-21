package com.example.marathon.service;

import com.example.marathon.dto.captcha.CaptchaResponse;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.util.Base64;
import java.util.Properties;
import java.util.UUID;

import jakarta.annotation.PostConstruct;
import javax.imageio.ImageIO;

@Slf4j
@Service
public class CaptchaService {

    private DefaultKaptcha kaptcha;
    private final Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofMinutes(2))
            .maximumSize(10000)
            .build();

    @PostConstruct
    public void init() {
        Properties props = new Properties();
        props.put("kaptcha.border", "no");
        props.put("kaptcha.textproducer.font.color", "black");
        props.put("kaptcha.image.width", "120");
        props.put("kaptcha.image.height", "40");
        props.put("kaptcha.textproducer.char.length", "4");
        props.put("kaptcha.textproducer.font.size", "32");
        Config config = new Config(props);
        kaptcha = new DefaultKaptcha();
        kaptcha.setConfig(config);
    }

    public CaptchaResponse generate() {
        String text = kaptcha.createText();
        BufferedImage image = kaptcha.createImage(text);
        String id = UUID.randomUUID().toString();
        // 忽略大小写
        cache.put(id, text.toLowerCase());
        log.info("Captcha generated: {}", id);
        log.info("Captcha text: {}", text);
        String base64 = encode(image);
        return new CaptchaResponse(id, base64, 120);
    }

    public boolean validate(String id, String code) {
        if (id == null || code == null) {
            return false;
        }
        String expected = cache.getIfPresent(id);
        if (expected == null) {
            return false;
        }
        boolean ok = expected.equalsIgnoreCase(code.trim());
        if (ok) {
            cache.invalidate(id);
        }
        return ok;
    }

    private String encode(BufferedImage image) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("captcha encode error", e);
        }
    }
}
