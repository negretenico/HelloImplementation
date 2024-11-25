package com.helloimplementation.dropdown.files.util;

import com.helloimplementation.dropdown.files.model.FileMetadata;

public class FilePathProvider {
    public  static  String getFilePath(FileMetadata fileMetadata){
        return String.join(".",fileMetadata.getName(),fileMetadata.getExtension());
    }
}
