package com.helloimplementation.fbpostsearch.post.service;

import com.helloimplementation.fbpostsearch.post.model.Post;
import com.helloimplementation.fbpostsearch.post.model.LikeUpdateResult;
import com.helloimplementation.fbpostsearch.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    PostRepository repository;
    public PostService(PostRepository repository){
        this.repository=repository;
    }

    public Post create(String content){
        return repository.save(Post.builder().content(content).numLikes(0).build());
    }
    @Transactional
    public LikeUpdateResult updateLikes(int postId, int delta){
        int rows=repository.updatePostLikes(postId,delta);
        return rows ==0? LikeUpdateResult.FAILURE: LikeUpdateResult.SUCCESS;
    }
}
