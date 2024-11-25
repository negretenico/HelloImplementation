package com.helloimplementation.dropdown.files.repository;

import com.helloimplementation.dropdown.files.model.FileShares;
import com.helloimplementation.dropdown.files.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileSharesRepository extends JpaRepository<FileShares, Integer> {
    @Query("SELECT fs.user FROM FileShares fs WHERE fs.fileMetadata.id = :fileId")
    List<User> findUsersByFileId(@Param("fileId") int fileId);
}
