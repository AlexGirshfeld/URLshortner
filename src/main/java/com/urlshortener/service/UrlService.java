package com.urlshortener.service;

import com.urlshortener.model.Url;
import com.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String originalUrl) {
        Url url = urlRepository.findByOriginalUrl(originalUrl);
        if (url != null) {
            return url.getShortUrl();
        }
        // Generate a short URL (simple example using hashCode)
        String shortUrl = Integer.toHexString(originalUrl.hashCode());
        Url newUrl = new Url();
        newUrl.setOriginalUrl(originalUrl);
        newUrl.setShortUrl(shortUrl);
        urlRepository.save(newUrl);
        return shortUrl;
    }

    @Cacheable(value = "urls", key = "#shortUrl")
    public String getOriginalUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        return url != null ? url.getOriginalUrl() : null;
    }
}