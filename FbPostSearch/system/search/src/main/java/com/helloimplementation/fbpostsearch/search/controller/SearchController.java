package com.helloimplementation.fbpostsearch.search.controller;

import com.helloimplementation.fbpostsearch.search.model.Post;
import com.helloimplementation.fbpostsearch.search.redis.InvertedIndex;
import com.helloimplementation.fbpostsearch.search.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RequestMapping("/api/search")
@RestController
public class SearchController {
    private final InvertedIndex invertedIndex;
    private final PostService postService;
    public SearchController(PostService postService, InvertedIndex invertedIndex){
        this.invertedIndex=invertedIndex;
        this.postService=postService;
    }
    @GetMapping
    public List<Post> search(@RequestParam String keyword) {
        Set<Integer> postIds = invertedIndex.search(keyword);  // Get post IDs from Redis
        List<Integer> postIdsAsLong = postIds.stream().toList();
        return postService.findAllByIds(postIdsAsLong);
    }
}
