package com.helloimplementation.youtube.service.upload.presign.provider;

import com.helloimplementation.youtube.models.Result;

public class LocalPresignedProvider implements PresignedUrlProvider {
    @Override
    public Result<String> getUrl(String fileName) {
        return Result.success("http://local-presignedurl.com");
    }
}
