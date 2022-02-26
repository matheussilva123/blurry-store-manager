package br.com.dias.blurrystoremanager.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductException {

    private int status;
    private long timestamp;
    private String exception;
    private String message;

}
