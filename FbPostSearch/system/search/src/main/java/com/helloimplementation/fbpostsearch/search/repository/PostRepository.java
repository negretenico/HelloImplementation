package com.helloimplementation.fbpostsearch.search.repository;

import com.helloimplementation.fbpostsearch.search.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository  extends JpaRepository<Post, Integer> {
}
