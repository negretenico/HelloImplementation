package com.helloimplementation.dropdown.files.download;

import com.helloimplementation.dropdown.files.model.FileMetadata;
import com.helloimplementation.dropdown.files.service.FileMetadataService;
import com.helloimplementation.dropdown.files.upload.CloudUploader;
import com.helloimplementation.dropdown.files.util.FilePathProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Profile("!local")
@Service
public class CloudDownloader implements Downloader{
    FileMetadataService fileDataLayerService;
    private final S3AsyncClient s3Client;
    private final FileMetadataService fileMetadataService;
    private final String bucketName;

    public CloudDownloader(S3AsyncClient s3Client, FileMetadataService fileMetadataService,  @Value("${s3.bucket.name}")String bucketName) {
        this.s3Client = s3Client;
        this.fileMetadataService = fileMetadataService;
        this.bucketName=bucketName;
    }

    @Override
    public Optional<Resource> download(int fileId) {
        // Fetch file metadata
        Optional<FileMetadata> optionalFileMetadata = fileMetadataService.getById(fileId);
        if (optionalFileMetadata.isEmpty()) {
            return Optional.empty(); // Return null or throw an exception to indicate file not found
        }

        FileMetadata fileMetadata = optionalFileMetadata.get();
        String s3Key = FilePathProvider.getFilePath(fileMetadata);
        Path tempFilePath = Path.of(System.getProperty("java.io.tmpdir"), s3Key);

        // Create the GetObjectRequest
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .build();

        try {
            CompletableFuture<GetObjectResponse> future = s3Client.getObject(getObjectRequest, AsyncResponseTransformer.toFile(tempFilePath));
            future.join();
            return Optional.of(new InputStreamResource(new FileInputStream(tempFilePath.toFile())));

        } catch (Exception e) {
            // Log the exception (use a logger)
            System.err.println("Error downloading file from S3: " + e.getMessage());
            return Optional.empty(); // Return null or throw an exception to indicate file not found
        }
    }
}
