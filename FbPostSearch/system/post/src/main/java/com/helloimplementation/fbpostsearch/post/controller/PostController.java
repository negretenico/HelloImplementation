package com.helloimplementation.fbpostsearch.post.controller;

import com.helloimplementation.fbpostsearch.post.model.LikeUpdateResult;
import com.helloimplementation.fbpostsearch.post.model.Post;
import com.helloimplementation.fbpostsearch.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/post")
@RestController
public class PostController {
    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Post> create(@RequestBody Post post) {
        Post newPost = postService.create(post.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    @PostMapping(value = "/{id}/like",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> like(@PathVariable int id) {
        LikeUpdateResult likeUpdateResult = postService.updateLikes(id, 1);
        return likeUpdateResult.equals(LikeUpdateResult.SUCCESS) ? ResponseEntity.ok(String.format("Updated the like count of %s",id)) : ResponseEntity.internalServerError().body(String.format("Failed to update the post with id %s", id));
    }
}
