package com.helloimplementation.dropdown.files.download;

import com.helloimplementation.dropdown.files.model.FileMetadata;
import com.helloimplementation.dropdown.files.service.FileMetadataService;
import com.helloimplementation.dropdown.files.util.FilePathProvider;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

@Service
@Profile("local")
public class LocalDownloader implements Downloader{
    FileMetadataService dataLayerService;
    public LocalDownloader(FileMetadataService dataLayerService){
        this.dataLayerService=dataLayerService;
    }
    private File createFile(String path){
        try{
            return new File(path);
        }catch (Exception e){
            return null;
        }
    }
    @Override
    public Optional<Resource> download(int fileId) {
        Optional<FileMetadata> possiblyFileMetaData = dataLayerService.getById(fileId);
        if(possiblyFileMetaData.isEmpty()){
            return Optional.empty();
        }
        FileMetadata fileMetadata = possiblyFileMetaData.get();
        String fileName = Path.of("local_uploads", FilePathProvider.getFilePath(fileMetadata)).toString();
        try {
            File file = createFile(fileName);
            if(Objects.isNull(file)){
                return Optional.empty();
            }
            return Optional.of( new InputStreamResource(new FileInputStream(file)));
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
