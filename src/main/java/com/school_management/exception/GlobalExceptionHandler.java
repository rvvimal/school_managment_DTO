package com.school_management.exception;

import com.school_management.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

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

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseDTO> handleStudentException(final Exception exception, WebRequest request) {
        exception.printStackTrace();
        ResponseDTO responseDTO = new ResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationErrors(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getFieldErrors();
        String errorDetails = "";
        if (!fieldErrors.isEmpty()) {
            FieldError firstError = fieldErrors.get(fieldErrors.size() - 1);
            errorDetails = firstError.getField() + " : " + firstError.getDefaultMessage();
        }

        ResponseDTO responseDTO = new ResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                errorDetails,
                null
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
