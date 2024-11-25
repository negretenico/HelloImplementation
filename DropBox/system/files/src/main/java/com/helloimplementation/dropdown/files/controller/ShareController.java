package com.helloimplementation.dropdown.files.controller;

import com.helloimplementation.dropdown.files.model.FailureResult;
import com.helloimplementation.dropdown.files.model.FileMetadata;
import com.helloimplementation.dropdown.files.model.Result;
import com.helloimplementation.dropdown.files.model.User;
import com.helloimplementation.dropdown.files.service.FileShareService;
import com.helloimplementation.dropdown.files.service.FileUploadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/file/")
@RestController
public class ShareController {
    FileShareService fileShareService;
    public ShareController(    FileShareService fileShareService){
        this.fileShareService=fileShareService;
    }
    @GetMapping(path="{id}/share")
    public ResponseEntity<List<User>> getSharedUsers(@PathVariable int id){
        return  ResponseEntity.ok(fileShareService.getSharedUsers(id));
    }
}
