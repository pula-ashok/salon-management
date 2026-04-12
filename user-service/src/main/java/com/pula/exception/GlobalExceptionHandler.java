package com.pula.exception;

import com.pula.payload.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(Exception exp, WebRequest req){
            ExceptionResponse response = new ExceptionResponse(
                    exp.getMessage(),
                    req.getDescription(false),
                    LocalDateTime.now()
            );
            return ResponseEntity.badRequest().body(response);
    }
}
