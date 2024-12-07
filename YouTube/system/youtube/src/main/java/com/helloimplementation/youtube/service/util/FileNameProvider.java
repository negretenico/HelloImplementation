package com.helloimplementation.youtube.service.util;

import com.helloimplementation.youtube.models.VideoMetadata;

public class FileNameProvider {
    public static String getFileName(VideoMetadata videoMetadata) {
        return String.join(".", videoMetadata.getName(), videoMetadata.getExtension());
    }
}
