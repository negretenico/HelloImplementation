package com.helloimplementation.dropdown.files.upload;

import com.helloimplementation.dropdown.files.model.FileMetadata;
import com.helloimplementation.dropdown.files.model.Result;

import java.io.IOException;

public interface Uploader {
    Result upload(FileMetadata fileMetadata) throws IOException;
}
