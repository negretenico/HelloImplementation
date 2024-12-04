package com.helloimplementation.youtube.models;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class VideoMetadata implements Serializable {
    String name;
    long videoMetadataId;
    List<String> s3Urls;
    String description;
    User user;
}
