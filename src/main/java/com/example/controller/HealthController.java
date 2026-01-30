package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repository.PasteRepository;
@RestController
public class HealthController {
	
	@Autowired
	private PasteRepository pasteRepository; // can be mocked or optional for now


	@GetMapping("/api/healthz")
	public Map<String, Boolean> health() {
	try {
	// optional DB check, can remove if PasteRepository not ready
	// pasteRepository.count();
	return Map.of("ok", true);
	} catch (Exception e) {
	return Map.of("ok", false);
	}
	}
		}
	/*@GetMapping("/api/healthz")
	public String health() {
	return "ok";
	}*/
	


