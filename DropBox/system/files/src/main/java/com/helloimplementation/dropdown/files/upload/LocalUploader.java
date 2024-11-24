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
import java.util.Objects;

@Profile("local")
@Service
public class LocalUploader implements Uploader {
    private boolean checkIfLocalDirectoryExists(){
        try{
            File directory = new File("local_uploads");
            return directory.exists() || directory.mkdirs();
        }catch (Exception e){
            return false;
        }

    }
    private FileWriter createFileWriter(String fileName){
        try {
            return new FileWriter(fileName);
        }
        catch(Exception e){
            return null;
        }
    }
    @Override
    public Result upload(FileMetadata fileMetadata) throws IOException {
        String fileName = Path.of("local_uploads",String.join(".", fileMetadata.getName(), fileMetadata.getExtension())).toString();
        if(!checkIfLocalDirectoryExists()){
            return Result.fail("We could not create the local directory");
        }
        try ( FileWriter fileWriter = createFileWriter(fileName);){
            if(Objects.isNull(fileWriter)){
                return  Result.fail("We could not create our filewriter");
            }
            fileWriter.write("This is where we would write th contents of the file");
        }catch (Exception e) {
            return Result.fail("Could not write the contents of the file");
        }
        return Result.ok(String.format("We successfully wrote the files contents to %s", fileName));
    }
}
