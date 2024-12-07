package com.helloimplementation.youtube.service.model;

import com.helloimplementation.youtube.models.Result;
import com.helloimplementation.youtube.models.VideoMetadata;
import com.helloimplementation.youtube.service.CacheService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VideoMetadataService {
    CacheService<VideoMetadata> videoMetadataCacheService;

    public VideoMetadataService(CacheService<VideoMetadata> videoMetadataCacheService) {
        this.videoMetadataCacheService = videoMetadataCacheService;
    }

    public Result<VideoMetadata> save(VideoMetadata videoMetadata) {
        UUID id = UUID.randomUUID();
        Result<VideoMetadata> saved = videoMetadataCacheService.save(id, videoMetadata);
        if (saved.isFailure()) {
            return Result.failure(String.format("Could not save the metadata, got error=%s", saved.getErrMsg()));
        }
        return Result.success(saved.getData());
    }

    public Result<VideoMetadata> getById(String uuid) {
        UUID id;
        try {
            id = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            return Result.failure(String.format("Could not create ID=%s", uuid));
        }
        Result<VideoMetadata> videoMetadataResult = videoMetadataCacheService.getItem(id);
        if (videoMetadataResult.isFailure()) {
            return Result.failure(String.format("Could not find meta data with id=%s with error=%s", uuid, videoMetadataResult.getErrMsg()));
        }
        return videoMetadataResult;
    }
}
