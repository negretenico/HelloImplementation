package com.helloimplementation.dropdown.files.upload;

import com.helloimplementation.dropdown.files.model.FileMetadata;
import com.helloimplementation.dropdown.files.model.FileUploadStatus;
import com.helloimplementation.dropdown.files.model.Result;
import com.helloimplementation.dropdown.files.service.FileMetadataService;
import com.helloimplementation.dropdown.files.util.FilePathProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Profile("!local")
@Service
public class CloudUploader implements Uploader {


    private final S3AsyncClient s3Client;
    private final FileMetadataService fileMetadataService;

    private final String bucketName;

    public CloudUploader(S3AsyncClient s3Client, FileMetadataService fileMetadataService,    @Value("${s3.bucket.name}")String bucketName) {
        this.s3Client = s3Client;
        this.fileMetadataService = fileMetadataService;
        this.bucketName=bucketName;
    }

    @Override
    public Result upload(FileMetadata fileMetadata) throws IOException {
        fileMetadataService.updateStatus(fileMetadata.getId(), FileUploadStatus.STARTED);
        Path path = Path.of(String.join(".", FilePathProvider.getFilePath(fileMetadata)));
        String fileName = path.toString();
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .metadata(Map.of("company", "hello-implementation"))
                .build();
        AsyncRequestBody requestBody = AsyncRequestBody.fromFile(path);
        try {
            fileMetadataService.updateStatus(fileMetadata.getId(), FileUploadStatus.INPROGRESS);
            CompletableFuture<PutObjectResponse> future = s3Client.putObject(request, requestBody);
            future.handle((response, throwable) -> {
                if (Objects.nonNull(throwable)) {
                    fileMetadataService.updateStatus(fileMetadata.getId(), FileUploadStatus.FAILED);
                } else {
                    fileMetadataService.updateStatus(fileMetadata.getId(), FileUploadStatus.SUCCESS);
                }
                return response;
            });
            return Result.ok("Upload initiated successfully");

        } catch (Exception e) {
            fileMetadataService.updateStatus(fileMetadata.getId(), FileUploadStatus.FAILED);
            return Result.fail("Failed to upload file to S3");
        }
    }
}
