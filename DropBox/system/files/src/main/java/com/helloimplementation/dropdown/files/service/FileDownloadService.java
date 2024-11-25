package com.helloimplementation.dropdown.files.service;

import com.helloimplementation.dropdown.files.download.Downloader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileDownloadService {
    Downloader downloader;
    public FileDownloadService(Downloader downloader){
        this.downloader=downloader;
    }
    public Optional<Resource> download(int fileId){
        return downloader.download(fileId);
    }
}
