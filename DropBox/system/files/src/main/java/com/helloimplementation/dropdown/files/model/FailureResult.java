package com.helloimplementation.dropdown.files.model;

public class FailureResult {
    public static boolean isFailure(Result result){
        return result.getStatus().equals(Result.Status.FAILURE);
    }
}
