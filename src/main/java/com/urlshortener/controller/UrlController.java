package com.urlshortener.controller;

import com.urlshortener.service.UrlService;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody String originalUrl) {
        return urlService.shortenUrl(originalUrl);
    }

    @GetMapping("/{shortUrl}")
public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
    String originalUrl = urlService.getOriginalUrl(shortUrl);
    if (originalUrl != null) {
        // Redirect to the original URL
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
    } else {
        // Return 404 if the shortened URL is not found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    }
}