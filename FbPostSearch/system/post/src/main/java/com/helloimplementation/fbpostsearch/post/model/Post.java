package com.helloimplementation.fbpostsearch.post.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "posts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Relies on database for auto-increment
    @Column(name = "post_id")
    int postId;
    @Column(name = "content", columnDefinition = "TEXT")
    String content;
    @Column(name = "num_likes")
    int numLikes;
}
