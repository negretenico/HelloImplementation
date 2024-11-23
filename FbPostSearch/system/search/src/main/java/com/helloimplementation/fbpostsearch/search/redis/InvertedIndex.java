package com.helloimplementation.fbpostsearch.search.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Component
public class InvertedIndex {
    private final RedisTemplate<String, Integer> redisTemplate;

    public InvertedIndex(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    // Method to search for posts by keyword
    public Set<Integer> search(String keyword) {
        return redisTemplate.opsForSet().members(keyword.toLowerCase());
    }
}
