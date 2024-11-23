package com.helloimplementation.fbpostsearch.post.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloimplementation.fbpostsearch.post.model.Post;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    ObjectMapper mapper = new ObjectMapper();
    public void sendPostToKafka(Post post) throws JsonProcessingException {
        String postMessage = mapper.writeValueAsString(post);
        kafkaTemplate.send("post-creation-topic", String.format("%d",post.getPostId()), postMessage);
    }
}
