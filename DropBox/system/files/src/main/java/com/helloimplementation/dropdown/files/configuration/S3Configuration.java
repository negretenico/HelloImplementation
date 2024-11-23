package com.helloimplementation.dropdown.files.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;

@Profile("!local")
@Configuration
public class S3Configuration {
    @Bean
    public AwsCredentials credentials(@Value("${s3.credentials.access}")String access, @Value("${s3.credentials.secret}")String secret){
        return AwsBasicCredentials.create(access,secret);
    }
    @Bean
    public S3AsyncClient s3Client(@Value("${s3.region}")String region, AwsCredentials credentials){
        return S3AsyncClient.builder().region(Region.of(region)).credentialsProvider(StaticCredentialsProvider.create(credentials)).build();
    }
}
