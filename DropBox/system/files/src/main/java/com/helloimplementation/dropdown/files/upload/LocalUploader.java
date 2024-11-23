package com.helloimplementation.dropdown.files.upload;

import com.helloimplementation.dropdown.files.model.FileMetadata;
import com.helloimplementation.dropdown.files.model.Result;
import com.helloimplementation.dropdown.files.repository.UserRepository;
import com.helloimplementation.dropdown.files.service.FileMetadataService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

@Profile("local")
@Service
public class LocalUploader implements Uploader {
    @Override
    public Result upload(FileMetadata fileMetadata) throws IOException {
        FileWriter fileWriter;
        String fileName = Path.of(String.join("/", fileMetadata.getName(), fileMetadata.getExtension())).toString();
        try {
             fileWriter= new FileWriter(fileName);
        }
        catch(Exception e){
            return Result.fail("Could not create the file writer");
            }
        try{
            fileWriter.write("This is where we would write th contents of the file");
        }catch (Exception e){
            return Result.fail("Could not write the contents of the file");
        }finally {
            fileWriter.close();
        }
        return Result.ok(String.format("We successfully wrote the files contents to %s", fileName));
    }
}
