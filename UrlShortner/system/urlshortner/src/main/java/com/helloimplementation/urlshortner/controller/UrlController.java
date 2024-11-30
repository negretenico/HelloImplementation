package com.helloimplementation.urlshortner.controller;

import com.helloimplementation.urlshortner.model.Result;
import com.helloimplementation.urlshortner.model.Url;
import com.helloimplementation.urlshortner.service.UrlService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/api/url")
@RestController
public class UrlController {
    UrlService urlService;
    public UrlController(UrlService urlService) {
        this.urlService=urlService;
    }
    @PostMapping(path="", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Url>  create(@RequestBody Url incomingUrl){
        Result<Url> result = urlService.save(incomingUrl);
        if(!result.isSuccess()){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(result.getData());
    }
    @GetMapping(path = "/redirect")
    public RedirectView redirectView(@RequestParam String shortenedUrl){
       Result<Url> shortUrl = urlService.getOriginalFromShortened(shortenedUrl);
       if(!shortUrl.isSuccess()){
           return new RedirectView("/404");
       }
       RedirectView redirectView = new RedirectView();
       redirectView.setUrl(shortUrl.getData().getOriginalUrl());
       return  redirectView;
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<Url> getUrlEntity(@PathVariable int id) {
        Result<Url> result = urlService.getById(id);
        if (!result.isSuccess()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result.getData());
    }
}
