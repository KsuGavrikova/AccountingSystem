package com.senla.training.accounting_system.dto;

import lombok.Data;

@Data
public class RegistrationUserDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
