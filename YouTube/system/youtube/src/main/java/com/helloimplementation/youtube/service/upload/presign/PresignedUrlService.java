package com.helloimplementation.youtube.service.upload.presign;

import com.helloimplementation.youtube.models.Result;
import com.helloimplementation.youtube.models.VideoMetadata;
import com.helloimplementation.youtube.service.upload.presign.provider.PresignedUrlProvider;
import com.helloimplementation.youtube.service.util.FileNameProvider;
import org.springframework.stereotype.Service;

@Service
public class PresignedUrlService {
    PresignedUrlProvider presignedUrlProvider;

    public PresignedUrlService(PresignedUrlProvider presignedUrlProvider) {
        this.presignedUrlProvider = presignedUrlProvider;
    }

    public Result<String> getPresignedUrl(VideoMetadata metadata) {
        String fileName = FileNameProvider.getFileName(metadata);
        Result<String> result = presignedUrlProvider.getUrl(fileName);
        if (result.isFailure()) {
            return Result.failure(String.format("Could not get a presigned url for fileName=%s, got error=%s", fileName, result.getErrMsg()));
        }
        return Result.success(result.getData());
    }
}
