package com.helloimplementation.dropdown.files.download;

import com.helloimplementation.dropdown.files.model.Result;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import java.util.Optional;

public interface Downloader {
    Optional<Resource> download(int fileId);
}
