package com.helloimplementation.urlshortner.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public  class Result<T> {
    private OperationStatus status;
    private String errorMsg;
    T data;
    public static <T> Result<T> success( T data){
        return Result.<T>builder().status(OperationStatus.SUCCESS).data(data).build();
    }
    public static <T> Result<T>  failure(String errorMsg){
        return Result.<T>builder().status(OperationStatus.FAILURE).errorMsg(errorMsg).build();
    }
    public boolean isSuccess(){
        return this.status.equals(OperationStatus.SUCCESS);
    }
}
