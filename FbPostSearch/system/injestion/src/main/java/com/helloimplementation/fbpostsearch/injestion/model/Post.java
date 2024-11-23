package com.helloimplementation.fbpostsearch.injestion.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Post {
    int postId;
    String content;
    int numLikes;
}
