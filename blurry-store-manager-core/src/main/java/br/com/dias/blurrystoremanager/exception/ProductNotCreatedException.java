package br.com.dias.blurrystoremanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProductNotCreatedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProductNotCreatedException(String message) {
        super(message);
    }

}
