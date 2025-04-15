package com.estsoft.restapiaccessdata.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //IllegalArgumentException 500x > 4xx
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ErrorHandler> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorHandler("404", ex.getMessage()));
    }

}
