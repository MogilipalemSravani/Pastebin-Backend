package com.example.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.PasteRepository;
import com.example.entity.Paste;

@Service
public class PasteService {
@Autowired
    private final PasteRepository repository;

    public PasteService(PasteRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Paste create(String content, Integer ttlSeconds, Integer maxViews) {
        Paste paste = new Paste();
        paste.setContent(content);
        paste.setMaxViews(maxViews);
        paste.setCreatedAt(Instant.now()); // Set creation time
        paste.setViewCount(0); // Initialize view count

        if (ttlSeconds != null && ttlSeconds > 0) {
            paste.setExpiresAt(Instant.now().plusSeconds(ttlSeconds));
        }

        return repository.save(paste);
    }

    // FETCH
    public Paste fetch(String id, Instant now) {
        Paste paste = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paste not found"));

        // Expiry check
        if (paste.getExpiresAt() != null && paste.getExpiresAt().isBefore(now)) {
            throw new RuntimeException("Paste expired");
        }

        // View limit check
        if (paste.getMaxViews() != null &&
            paste.getViewCount() >= paste.getMaxViews()) {
            throw new RuntimeException("Paste view limit exceeded");
        }

        // Increment view count
        paste.setViewCount(paste.getViewCount() + 1);
        repository.save(paste);

        return paste;
    }

	public Paste fetch(String id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}
}

	

	

	
