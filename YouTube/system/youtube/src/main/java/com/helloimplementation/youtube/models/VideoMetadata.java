package com.helloimplementation.youtube.models;


import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
public class VideoMetadata implements Serializable {
    String name;
    String extension;
    UUID videoMetadataId;
    List<String> s3Urls;
    String description;
    User user;
}
