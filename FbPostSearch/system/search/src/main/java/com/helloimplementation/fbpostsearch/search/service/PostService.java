package com.helloimplementation.fbpostsearch.search.service;

import com.helloimplementation.fbpostsearch.search.model.Post;
import com.helloimplementation.fbpostsearch.search.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    PostRepository repository;
    public PostService(PostRepository repository){
        this.repository=repository;
    }

    public List<Post> findAllByIds(List<Integer> ids){
        return repository.findAllById(ids);
    }

}
