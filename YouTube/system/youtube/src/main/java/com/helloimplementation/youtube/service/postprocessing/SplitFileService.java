package com.helloimplementation.youtube.service.postprocessing;

import com.helloimplementation.youtube.models.Result;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SplitFileService {

    public Result<List<File>> splitFile(File file, long chunkSize) {
        List<File> chunks = new ArrayList<>();
        byte[] buffer = new byte[1024];
        int bytesRead;
        long totalBytesRead = 0;
        int chunkIndex = 1;
        try (InputStream inputStream = new FileInputStream(file)) {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                totalBytesRead += bytesRead;
                File chunkFile = new File(file.getParent(), file.getName() + ".part" + chunkIndex);
                try (OutputStream outputStream = new FileOutputStream(chunkFile, true)) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                if (totalBytesRead >= chunkSize) {
                    chunks.add(chunkFile);  // Add the chunk file to the list
                    chunkIndex++;
                    totalBytesRead = 0;
                }
            }
        } catch (Exception e) {
            return Result.failure(String.format("We ran into an error splitting the file, error=%s", e.getLocalizedMessage()));
        }

        // Handle case if the last chunk is less than the chunkSize
        if (totalBytesRead > 0) {
            File chunkFile = new File(file.getParent(), file.getName() + ".part" + chunkIndex);
            chunks.add(chunkFile);
        }
        return Result.success(chunks);
    }
}
