package com.helloimplementation.youtube.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Profile("!local")
@Configuration
public class S3Config {
    @Bean
    public AwsCredentials credentials(@Value("${s3.credentials.access}") String access, @Value("${s3.credentials.secret}") String secret) {
        return AwsBasicCredentials.create(access, secret);
    }

    @Bean
    public S3Presigner s3Client(@Value("${s3.region}") String region, AwsCredentials credentials) {
        return S3Presigner.builder().region(Region.of(region)).credentialsProvider(StaticCredentialsProvider.create(credentials)).build();
    }
}
