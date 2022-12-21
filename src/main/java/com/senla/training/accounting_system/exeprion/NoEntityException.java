package com.senla.training.accounting_system.exeprion;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class NoEntityException extends RuntimeException {

    private String message;

    private Long id;

    public NoEntityException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message + id + " not found";
    }
}