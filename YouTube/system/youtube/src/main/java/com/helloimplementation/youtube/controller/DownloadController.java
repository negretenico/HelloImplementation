package com.helloimplementation.youtube.controller;

import com.helloimplementation.youtube.models.Result;
import com.helloimplementation.youtube.models.VideoMetadata;
import com.helloimplementation.youtube.service.model.VideoMetadataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/video")
public class DownloadController {
    VideoMetadataService videoMetadataService;

    public DownloadController(VideoMetadataService videoMetadataService) {
        this.videoMetadataService = videoMetadataService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VideoMetadata> getVideo(@PathVariable String uuid) {
        Result<VideoMetadata> result = videoMetadataService.getById(uuid);
        if (result.isFailure()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result.getData());
    }
}
