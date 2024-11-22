package com.cotede.e2eechatapp.exceptions;
import com.cotede.e2eechatapp.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;
    @ExceptionHandler(CustomExceptions.UserNotFound.class)
    public ResponseEntity<ApiResponse<Void>> handleUserNotFoundException(CustomExceptions.UserNotFound ex) {
        String message = messageSource.getMessage("user.not.found", new Object[]{ex.getMessage()}, LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(message, HttpStatus.CONFLICT));
    }
}
