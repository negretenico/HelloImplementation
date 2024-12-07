package com.helloimplementation.youtube.controller;

import com.helloimplementation.youtube.models.Result;
import com.helloimplementation.youtube.models.VideoMetadata;
import com.helloimplementation.youtube.service.model.VideoMetadataService;
import com.helloimplementation.youtube.service.upload.presign.PresignedUrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/video")
public class UploadController {
    VideoMetadataService videoMetadataService;
    PresignedUrlService presignedUrlService;

    public UploadController(VideoMetadataService videoMetadataService, PresignedUrlService presignedUrlService) {
        this.videoMetadataService = videoMetadataService;
        this.presignedUrlService = presignedUrlService;
    }

    @PostMapping(value = "/presigned-url")
    public ResponseEntity<String> getPresignedUrl(@Validated @RequestBody VideoMetadata videoMetadata) {
        Result<VideoMetadata> videoMetadataResult = videoMetadataService.save(videoMetadata);
        if (videoMetadataResult.isFailure()) {
            return ResponseEntity.unprocessableEntity().body(videoMetadataResult.getErrMsg());
        }
        Result<String> urlResult = presignedUrlService.getPresignedUrl(videoMetadataResult.getData());
        if (urlResult.isFailure()) {
            return ResponseEntity.unprocessableEntity().body(urlResult.getErrMsg());
        }
        return ResponseEntity.ok(urlResult.getData());
    }
}
