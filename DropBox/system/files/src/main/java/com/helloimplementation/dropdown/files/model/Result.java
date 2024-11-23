package com.helloimplementation.dropdown.files.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Result {
    enum Status {
        SUCCESS,
        FAILURE
    }
    private Status status;
    private String msg;
    public static  Result ok(String message){
        return Result.builder().status(Status.SUCCESS).msg(message).build();
    }
    public static  Result fail(String message){
        return Result.builder().status(Status.FAILURE).msg(message).build();
    }
}
