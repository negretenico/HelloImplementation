package com.helloimplementation.fbpostsearch.injestion.redis;

import com.helloimplementation.fbpostsearch.injestion.model.Post;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class InvertedIndex {
    private final RedisTemplate<String, Integer> redisTemplate;

    public InvertedIndex(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Method to add a post to the inverted index
    public void addPost(Post post) {
        //Note we can enhance this by using an n-gram approach
        String[] words = post.getContent().toLowerCase().split("\\W+");
        Arrays.stream(words).forEach(word->redisTemplate.opsForSet().add(word,post.getPostId()));

    }

    // Method to search for posts by keyword
    public Set<Integer> search(String keyword) {
        return redisTemplate.opsForSet().members(keyword.toLowerCase());
    }
}
