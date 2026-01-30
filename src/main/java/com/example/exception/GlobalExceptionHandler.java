package com.example.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
	public class GlobalExceptionHandler {

	    @ExceptionHandler(PasteUnavailableException.class)
	    public ResponseEntity<Map<String, String>> handleUnavailable(
	            PasteUnavailableException ex) {

	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("error", ex.getMessage()));
	    }
	}

