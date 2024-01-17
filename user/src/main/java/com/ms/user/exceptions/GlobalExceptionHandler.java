package com.ms.user.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleDataAccessException(DataAccessException e) {
        log.error("Tratando exceção de acesso a dados", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro de acesso aos dados");
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        log.error("Tratando exceção de e-mail já existente", e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já cadastrado");
    }
}
