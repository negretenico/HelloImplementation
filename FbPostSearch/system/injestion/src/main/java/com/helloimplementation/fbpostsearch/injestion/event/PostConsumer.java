package com.helloimplementation.fbpostsearch.injestion.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloimplementation.fbpostsearch.injestion.model.Post;
import com.helloimplementation.fbpostsearch.injestion.redis.InvertedIndex;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PostConsumer {
    private final InvertedIndex invertedIndex;
    ObjectMapper mapper= new ObjectMapper();
    public PostConsumer(InvertedIndex invertedIndex){
        this.invertedIndex=invertedIndex;
    }
    @KafkaListener(topics = "post-creation-topic", groupId = "post-group")
    public void processPost(String postMessage) throws JsonProcessingException {
        Post post = mapper.readValue(postMessage,Post.class);
        invertedIndex.addPost(post);
    }
}
