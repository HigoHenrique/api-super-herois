package io.github.higohenrique.super_heroi_api.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.github.higohenrique.super_heroi_api.dto.ApiErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceConflictException.class)
    public ApiErrorDTO handleResourceConflict(ResourceConflictException ex) {
        return new ApiErrorDTO(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequestException.class)
    public ApiErrorDTO handleInvalidRequest(InvalidRequestException ex) {
        return new ApiErrorDTO(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiErrorDTO handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {

        Throwable cause = ex.getCause();
        String message = (cause instanceof InvalidFormatException &&
                ((InvalidFormatException) cause).getTargetType().equals(LocalDate.class))
                ? "O formato da data é inválido. Por favor, utilize o formato 'dd/MM/yyyy'."
                : "O corpo da requisição está malformado ou contém dados em formato inválido.";

        return new ApiErrorDTO(message);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiErrorDTO handleResourceNotFound(ResourceNotFoundException ex) {
        return new ApiErrorDTO(ex.getMessage());
    }
}
