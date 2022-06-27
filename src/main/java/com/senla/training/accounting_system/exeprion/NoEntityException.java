package com.senla.training.accounting_system.exeprion;

public class EntityNotFoundException extends RuntimeException {

    private String message;

    private Long id;

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message) {
        this.message = message;
    }

    public EntityNotFoundException(String message, Long id) {
        this.message = message;
        this.id = id;
    }

    public String toString() {
        return message + id + " not found";
    }
}