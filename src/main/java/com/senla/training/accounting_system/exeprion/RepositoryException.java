package com.senla.training.accounting_system.exeprion;

public class RepositoryException extends RuntimeException {

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(Throwable course) {
        super(course);
    }
}
