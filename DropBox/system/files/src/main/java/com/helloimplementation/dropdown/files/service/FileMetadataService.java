package com.helloimplementation.dropdown.files.service;

import com.helloimplementation.dropdown.files.model.FileMetadata;
import com.helloimplementation.dropdown.files.model.FileUploadStatus;
import com.helloimplementation.dropdown.files.model.Result;
import com.helloimplementation.dropdown.files.repository.FileMetadataRepository;
import org.springframework.stereotype.Service;

@Service
public class FileMetadataService {
    FileMetadataRepository repository;
    public FileMetadataService(FileMetadataRepository repository){
        this.repository=repository;
    }
    public FileMetadata save(FileMetadata fileMetadata){
        return repository.save(fileMetadata);
    }
    public Result updateStatus(int fileMetadataId, FileUploadStatus status){
        try{
          int rowsAffected=   repository.updateStatus(fileMetadataId,status.name());
          return rowsAffected==0?Result.fail("Could not find any files to update"): Result.ok(String.format("Successfully updated the file %d",fileMetadataId));
        }catch (Exception e){
            return Result.fail(String.format("Could not update the status for %d",fileMetadataId));
        }
    }
}
