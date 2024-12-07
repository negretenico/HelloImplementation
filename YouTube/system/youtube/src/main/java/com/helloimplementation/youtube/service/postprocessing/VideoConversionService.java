package com.helloimplementation.youtube.service.postprocessing;

import com.helloimplementation.youtube.models.Result;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Profile("!local")
public class VideoConversionService {
    public Result<File> convertFile(File source, String bitrate) {
        File target = new File(String.format("%s-converteed", source.getName()));
        String ffmpegCommand = String.format("ffmpeg -i %s -b:v %s %s", source.getName(), bitrate, target.getName());
        ProcessBuilder processBuilder = new ProcessBuilder(ffmpegCommand.split(" "));
        processBuilder.directory(new File(""));  // Set the working directory if needed
        // Start the process
        Process process;
        try {
            process = processBuilder.start();
            process.waitFor();  // Wait for the process to finish
        } catch (Exception e) {
            return Result.failure("Could not convert the bitrate");
        }
        return Result.success(target);
    }
}
