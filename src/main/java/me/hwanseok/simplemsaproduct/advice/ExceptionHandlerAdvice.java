package me.hwanseok.simplemsaproduct.advice;

import me.hwanseok.simplemsaproduct.exception.ProductConstraintViolationException;
import me.hwanseok.simplemsaproduct.exception.ProductNotFoundException;
import me.hwanseok.simplemsaproduct.model.dto.common.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> notFoundException(ProductNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto.builder()
                .msg("Product Not Found")
                .build());
    }

    @ExceptionHandler(ProductConstraintViolationException.class)
    public ResponseEntity<ErrorDto> constraintViolationException(ProductConstraintViolationException e){
        return ResponseEntity.badRequest().body(ErrorDto.builder()
                .msg("Constraint Violation")
                .build());
    }
}
