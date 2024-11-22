package com.furkan.exception.handling.demo.model.dto.response;

import com.furkan.exception.handling.demo.exception.ErrorDetails;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        String status,
        T data,
        ErrorDetails error,
        LocalDateTime timestamp
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("SUCCESS",data,null,LocalDateTime.now());
    }
    public static <T> ApiResponse<T> error(T data, ErrorDetails error) {
        return new ApiResponse<>("ERROR",null,error,LocalDateTime.now());
    }

}
