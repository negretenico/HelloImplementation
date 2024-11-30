package com.helloimplementation.urlshortner.service;

import org.springframework.stereotype.Service;

@Service
public class ShortUrlService {
    Base10Service base10Service;

    public ShortUrlService(Base10Service base10Service){
        this.base10Service=base10Service;
    }
    public  String getShortUrl(){
        int currentNumber = base10Service.getCurrentNumber();
        if(currentNumber==0){
            return "http://example.com";
        }
        StringBuilder result =new StringBuilder();
        String characterSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int conversionFactor = 62;
        while (currentNumber >0){
            int remainder = currentNumber % conversionFactor;
            result.append(characterSet.charAt(remainder));
            currentNumber /= conversionFactor;
        }

        return result.reverse().toString();
    }
}