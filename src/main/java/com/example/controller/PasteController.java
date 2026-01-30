package com.example.controller;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.PasteResponse;
import com.example.entity.Paste;
import com.example.service.PasteService;

@RestController
@RequestMapping("/api/pastes")
@CrossOrigin(origins = "http://localhost:3000")
public class PasteController {
    private final PasteService service;

    public PasteController(PasteService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Paste create(@RequestBody Map<String, Object> body) {

        String content = (String) body.get("content");
        
        // Handle both "ttlSeconds" and "expiresInMinutes"
        Integer ttlSeconds = null;
        if (body.get("ttlSeconds") != null) {
            ttlSeconds = (Integer) body.get("ttlSeconds");
        } else if (body.get("expiresInMinutes") != null) {
            // Convert minutes to seconds
            ttlSeconds = (Integer) body.get("expiresInMinutes") * 60;
        }
        
        Integer maxViews = body.get("maxViews") == null
                ? null : (Integer) body.get("maxViews");

        return service.create(content, ttlSeconds, maxViews);
    }

    // FETCH
   /* @GetMapping("/{id}")
    public Paste fetch(@PathVariable String id,
                       @RequestHeader(value = "x-test-now-ms", required = false) Long testNow) {

        Instant now = (testNow != null)
                ? Instant.ofEpochMilli(testNow)
                : Instant.now();

        return service.fetch(id, now);
    }*/
   /* @GetMapping("/{id}")
    public ResponseEntity<Paste> viewPaste(@PathVariable String id) {
        Paste paste = service.fetch(id);

        if (paste == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(paste);
    }*/
  /*  @GetMapping("/{id}")
    public ResponseEntity<?> viewPaste(@PathVariable String id) {
        try {
            Paste paste = service.fetch(id);

            return ResponseEntity.ok(Map.of(
                "content", paste.getContent(),
                "remaining_views", paste.getMaxViews() == null
                    ? null
                    : paste.getMaxViews() - paste.getViewCount(),
                "expires_at", paste.getExpiresAt()
            ));

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                Map.of("error", e.getMessage())
            );
        }
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<?> viewPaste(@PathVariable String id) {
        Paste paste = service.fetch(id, Instant.now());

        Integer remainingViews = null;
        if (paste.getMaxViews() != null) {
            remainingViews = paste.getMaxViews() - paste.getViewCount();
        }

        PasteResponse response = new PasteResponse(
            paste.getContent(),
            remainingViews,
            paste.getExpiresAt()
        );

        return ResponseEntity.ok(response);
    }
    }
