package com.helloimplementation.whatsapp.model;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Result {
    Status status;
    String message;
    public static Result success(String message){
        return Result.builder().message(message).status(Status.SUCCESS).build();
    }
    public static Result failure(String message){
        return  Result.builder().message(message).status(Status.FAILURE).build();
    }
}
