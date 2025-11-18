package com.newbiecoder45.task_manager.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> 
    handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleTaskNotFound(TaskNotFoundException ex){
            Map<String,String> err = new HashMap<>();
            err.put("error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DuplicateTaskException.class)
    public ResponseEntity<Map<String,String>> handleDuplicateTask(DuplicateTaskException ex){
            Map<String,String> err = new HashMap<>();
            err.put("error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }
}
