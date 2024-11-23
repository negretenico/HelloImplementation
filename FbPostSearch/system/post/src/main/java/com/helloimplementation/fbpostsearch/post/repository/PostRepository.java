package com.helloimplementation.fbpostsearch.post.repository;

import com.helloimplementation.fbpostsearch.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository  extends JpaRepository<Post, Integer> {
    @Modifying
    @Query("UPDATE Post p SET p.numLikes = p.numLikes + :increment WHERE p.postId = :postId")
    int updatePostLikes(@Param("postId") int postId, @Param("increment") int increment);
}
