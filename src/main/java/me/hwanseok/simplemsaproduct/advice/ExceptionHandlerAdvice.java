package me.hwanseok.simplemsaproduct.advice;

import me.hwanseok.simplemsaproduct.exception.ProductConstraintViolationException;
import me.hwanseok.simplemsaproduct.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> notFoundException(ProductNotFoundException e){
        return ResponseEntity.badRequest().body("Product Not Found");
    }

    @ExceptionHandler(ProductConstraintViolationException.class)
    public ResponseEntity<String> constraintViolationException(ProductConstraintViolationException e){
        return ResponseEntity.badRequest().body("Constraint Violation");
    }
}
