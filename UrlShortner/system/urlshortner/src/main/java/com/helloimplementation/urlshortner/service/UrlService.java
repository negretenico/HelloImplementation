package com.helloimplementation.urlshortner.service;

import com.helloimplementation.urlshortner.model.Result;
import com.helloimplementation.urlshortner.model.Url;
import com.helloimplementation.urlshortner.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final ShortUrlService shortUrlService;
    public UrlService(UrlRepository urlRepository,ShortUrlService shortUrlService) {
        this.urlRepository=urlRepository;
        this.shortUrlService=shortUrlService;
    }
    public Result<Url> save(Url url){
        String shortened = shortUrlService.getShortUrl();
        Url toSave = new Url();
        toSave.setShortenedUrl(shortened);
        toSave.setOriginalUrl(url.getOriginalUrl());
        return Result.success( urlRepository.save(toSave));
    }

    public Result<Url> getOriginalFromShortened(String originalUrlName){
        Optional<Url> possibleUrl = urlRepository.findUrlByShortenedUrl(originalUrlName);
        if(possibleUrl.isEmpty()){
            return Result.failure(String.format("We could not find the short url for %s",originalUrlName));
        }
        return Result.success(possibleUrl.get());
    }
    public Result<Url> getById(long id){
        Optional<Url> possibleUrl = urlRepository.findById(id);
        if(possibleUrl.isEmpty()){
            return Result.failure(String.format("Could not find result with id %s",id));
        }
        return Result.success(possibleUrl.get());
    }
}
