package com.example.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GlobalExceptionHandle {
	@ExceptionHandler(ApiException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handle(ApiException ex) {
	return Map.of("error", ex.getMessage());
	}
}
