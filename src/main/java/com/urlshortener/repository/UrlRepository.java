package com.urlshortener.repository;

import com.urlshortener.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<Url, String> {
    Url findByShortUrl(String shortUrl);
    Url findByOriginalUrl(String originalUrl);
}