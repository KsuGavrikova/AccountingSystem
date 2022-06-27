package com.senla.training.accounting_system.exeprion;

public class NoEntityException extends RuntimeException {

    private String message;

    private Long id;

    public NoEntityException() {
        super();
    }

    public NoEntityException(String message) {
        this.message = message;
    }

    public NoEntityException(String message, Long id) {
        this.message = message;
        this.id = id;
    }

    @Override
    public String toString() {
        return message + id + " not found";
    }
}