package br.com.cursoudemy.productapi.config.exception;

import lombok.Data;

import java.time.Instant;

@Data
public class ExceptionDetails {
    private String message;
    private Integer status;
    private Instant moment;
    private String cause;
}
