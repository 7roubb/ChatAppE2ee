package com.cotede.e2eechatapp.exceptions;
import com.cotede.e2eechatapp.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(CustomExceptions.UserNotFound.class)
    public ResponseEntity<ApiResponse<Void>> handleUserNotFoundException(CustomExceptions.UserNotFound ex) {
        String message = messageSource.getMessage("user.not.found", new Object[]{ex.getMessage()}, LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(message, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(CustomExceptions.UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserAlreadyExistsException(CustomExceptions.UserAlreadyExistsException ex) {
        String message = messageSource.getMessage("username.already.exist", new Object[]{ex.getMessage()}, LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(message, HttpStatus.CONFLICT));
    }

    @ExceptionHandler(CustomExceptions.EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleEmailAlreadyExistsException(CustomExceptions.EmailAlreadyExistsException ex) {
        String message = messageSource.getMessage("email.already.used", new Object[]{ex.getMessage()}, LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(message, HttpStatus.CONFLICT));
    }

    @ExceptionHandler(CustomExceptions.FriendNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleFriendNotFoundException(CustomExceptions.FriendNotFoundException ex) {
        String message = messageSource.getMessage("friend.not.found", new Object[]{ex.getMessage()}, LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(message, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(CustomExceptions.FriendAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleFriendAlreadyExistsException(CustomExceptions.FriendAlreadyExistsException ex) {
        String message = messageSource.getMessage("friend.already.exist", new Object[]{ex.getMessage()}, LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(message, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Invalid user data")
                .content(errors)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}