package com.helloimplementation.dropdown.files.controller;


import com.helloimplementation.dropdown.files.model.FailureResult;
import com.helloimplementation.dropdown.files.model.FileMetadata;
import com.helloimplementation.dropdown.files.model.Result;
import com.helloimplementation.dropdown.files.service.FileUploadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/file/upload")
@RestController
public class UploadController {
    FileUploadService uploadService;
    public UploadController(FileUploadService uploadService){
        this.uploadService=uploadService;
    }
    @PostMapping(path="", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> uploadFile(@RequestBody FileMetadata fileMetadata){
        Result result=uploadService.upload(fileMetadata);
        return FailureResult.isFailure(result)?ResponseEntity.unprocessableEntity().body("We had an issue dealing with provided file") : ResponseEntity.ok("Success");
    }
}
