package com.helloimplementation.dropdown.files.service;

import com.helloimplementation.dropdown.files.model.FileShares;
import com.helloimplementation.dropdown.files.model.User;
import com.helloimplementation.dropdown.files.repository.FileSharesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileShareService {
    FileSharesRepository repository;
    public FileShareService(FileSharesRepository repository){
        this.repository=repository;
    }
    public List<User> getSharedUsers(int fileId){
        return repository.findUsersByFileId(fileId);
    }
}
