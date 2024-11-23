package com.helloimplementation.dropdown.files.repository;

import com.helloimplementation.dropdown.files.model.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Integer> {
    @Modifying
    @Query("UPDATE FileMetadata fmd SET fmd.status =:status WHERE fmd.file_id = :fileId")
    int updateStatus(@Param("fileId") int fileId, @Param("status") String status);
}
