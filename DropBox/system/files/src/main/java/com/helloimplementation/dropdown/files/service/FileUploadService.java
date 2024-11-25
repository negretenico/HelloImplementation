package com.helloimplementation.dropdown.files.service;

import com.helloimplementation.dropdown.files.model.FileMetadata;
import com.helloimplementation.dropdown.files.model.FileUploadStatus;
import com.helloimplementation.dropdown.files.model.Result;
import com.helloimplementation.dropdown.files.upload.Uploader;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileUploadService {
    Uploader uploader;
    public FileUploadService(Uploader uploader){
        this.uploader=uploader;
    }
    public Result upload(FileMetadata fileMetadata){
        try{
            return uploader.upload(fileMetadata);
        } catch (IOException e) {
            return Result.fail("We ran into an issue uploading the file");
        }
    }
}

