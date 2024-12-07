package com.helloimplementation.youtube.service.upload.presign.provider;

import com.helloimplementation.youtube.models.Result;

public interface PresignedUrlProvider {
    Result<String> getUrl(String fileName);
}


