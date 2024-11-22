package com.furkan.exception.handling.demo.exception.handler;

import com.furkan.exception.handling.demo.exception.BusinessException;
import com.furkan.exception.handling.demo.exception.ErrorCode;
import com.furkan.exception.handling.demo.exception.ErrorDetails;
import com.furkan.exception.handling.demo.exception.ResourceNotFoundException;
import com.furkan.exception.handling.demo.model.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException exc) {
       ErrorDetails errorDetails = ErrorDetails.of(exc.getMessage(), exc.getErrorCode());
       return ResponseEntity.badRequest().body(ApiResponse.error(null,errorDetails));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException exc) {
        ErrorDetails errorDetails = ErrorDetails.of(exc.getMessage(), exc.getErrorCode());
        return ResponseEntity.badRequest().body(ApiResponse.error(null,errorDetails));
    }

    //
   @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        validationErrors.put(error.getField(), error.getDefaultMessage())
                );

        ErrorDetails errorDetails = ErrorDetails.ofValidation(validationErrors);

        return ResponseEntity.badRequest()
                .body(ApiResponse.error(null,errorDetails));
    }


}
