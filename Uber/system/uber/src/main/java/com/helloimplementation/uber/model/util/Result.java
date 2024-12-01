package com.helloimplementation.uber.model.util;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Result<T> {
    T data;
    String errorMsg;
    EvaluationStatus status;
    public static <T>  Result<T>success(T data){
        return Result.<T>builder().status(EvaluationStatus.SUCCESS).data(data).build();
    }
    public static <T> Result<T>failure(String errorMsg){
        return Result.<T>builder().status(EvaluationStatus.FAILURE).errorMsg(errorMsg).build();
    }
    public  boolean isFailure(){
        return this.getStatus().equals(EvaluationStatus.FAILURE);
    }
}
