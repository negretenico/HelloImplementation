package com.helloimplementation.youtube.config;

import com.helloimplementation.youtube.models.User;
import com.helloimplementation.youtube.models.VideoMetadata;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class IgniteConfig {
    @Bean
    public Ignite igniteInstance(@Value("${cache.videoMetadata.name}") String videoMetadata, @Value("${cache.user.name}") String user) {
        IgniteConfiguration cfg = new IgniteConfiguration();

        // Enable peer class loading
        cfg.setPeerClassLoadingEnabled(true);

        // Cache configuration for key-value storage
        CacheConfiguration<UUID, VideoMetadata> videoMetadataCacheConfig = createCacheConfig(videoMetadata);
        CacheConfiguration<UUID, User> userCacheConfig = createCacheConfig(user);
        cfg.setCacheConfiguration(videoMetadataCacheConfig, userCacheConfig);

        // Start Ignite node in embedded mode
        return Ignition.start(cfg);
    }


    private <T> CacheConfiguration<UUID, T> createCacheConfig(String cacheName) {
        CacheConfiguration<UUID, T> cacheConfiguration = new CacheConfiguration<>(cacheName);
        cacheConfiguration.setCacheMode(CacheMode.PARTITIONED); // Adjust as needed
        cacheConfiguration.setAtomicityMode(CacheAtomicityMode.ATOMIC);
        return cacheConfiguration;
    }
}
