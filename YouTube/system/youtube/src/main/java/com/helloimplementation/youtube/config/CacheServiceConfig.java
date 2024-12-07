package com.helloimplementation.youtube.config;

import com.helloimplementation.youtube.models.User;
import com.helloimplementation.youtube.models.VideoMetadata;
import com.helloimplementation.youtube.service.CacheService;
import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheServiceConfig {
    Ignite igniteInstance;

    public CacheServiceConfig(Ignite igniteInstance) {
        this.igniteInstance = igniteInstance;
    }

    @Bean
    public CacheService<VideoMetadata> videoMetadataCacheService(@Value("${cache.videoMetadata.name}") String videoMetadata) {
        return new CacheService<>(igniteInstance.cache(videoMetadata));
    }

    @Bean
    CacheService<User> userCacheService(@Value("${cache.user.name}") String user) {
        return new CacheService<>(igniteInstance.cache(user));
    }
}
