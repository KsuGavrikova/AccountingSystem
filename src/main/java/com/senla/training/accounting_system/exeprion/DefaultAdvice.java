package com.senla.training.accounting_system.exeprion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<Response> repositoryException(RepositoryException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoEntityException.class)
    public ResponseEntity<Response> entityNotFoundException(NoEntityException e) {
        Response response = new Response(e.toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}