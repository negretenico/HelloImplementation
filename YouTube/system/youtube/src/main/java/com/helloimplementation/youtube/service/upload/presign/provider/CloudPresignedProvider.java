package com.helloimplementation.youtube.service.upload.presign.provider;

import com.helloimplementation.youtube.models.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.time.Duration;

@Profile("!local")
public class CloudPresignedProvider implements PresignedUrlProvider {
    S3Presigner s3Client;
    String bucketName;

    public CloudPresignedProvider(S3Presigner s3Client, @Value("${s3.bucket.name}") String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    @Override
    public Result<String> getUrl(String fileName) {
        String url;
        try {
            url = s3Client.presignGetObject(GetObjectPresignRequest.builder().signatureDuration(Duration.ofMinutes(10)).getObjectRequest(r -> r.bucket(bucketName).key(fileName)).build()).url().toString();
        } catch (Exception e) {
            return Result.failure(String.format("Could not save the file=%s to s3, error=%s", fileName, e.getLocalizedMessage()));
        }
        return Result.success(url);
    }
}
