package com.school_management.exception;

import com.school_management.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ResponseDTO> handleStudentNotFoundException(final UserNotFoundException exception, WebRequest request) {
        ResponseDTO responseDTO = new ResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
