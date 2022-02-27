package br.com.dias.blurrystoremanager.controller.exception;

import br.com.dias.blurrystoremanager.exception.ProductException;
import br.com.dias.blurrystoremanager.exception.ProductNotCreatedException;
import br.com.dias.blurrystoremanager.exception.ProductNotFoundException;
import br.com.dias.blurrystoremanager.exception.ProductNotUpdatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ProductNotCreatedException.class)
    public ResponseEntity<?> handleResourceNotCreatedException(ProductNotCreatedException exception) {
        final var productException = ProductException.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .exception(exception.getClass().toString())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(productException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException exception) {
        final var productException = ProductException.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .exception(exception.getClass().toString())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(productException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotUpdatedException.class)
    public ResponseEntity<?> handleProductNotUpdatedException(ProductNotUpdatedException exception) {
        final var productException = ProductException.builder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .exception(exception.getClass().toString())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(productException, HttpStatus.BAD_REQUEST);
    }

}
