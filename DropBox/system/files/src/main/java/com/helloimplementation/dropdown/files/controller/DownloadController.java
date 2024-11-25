package com.helloimplementation.dropdown.files.controller;

import com.helloimplementation.dropdown.files.service.FileDownloadService;
import org.apache.coyote.Response;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;

@RequestMapping("/api/file/download")
@RestController
public class DownloadController {
    FileDownloadService fileDownloadService;
    public DownloadController(FileDownloadService fileDownloadService){
        this.fileDownloadService=fileDownloadService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Resource> download(@PathVariable int id){
        Optional<Resource> possibleResource = fileDownloadService.download(id);
        return possibleResource.map(resource -> ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream") // Ensures binary file type for download
                .body(resource)).orElseGet(() -> ResponseEntity.unprocessableEntity().body(null));
    }
}
