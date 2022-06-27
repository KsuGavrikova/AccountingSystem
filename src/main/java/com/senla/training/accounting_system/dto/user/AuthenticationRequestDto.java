package com.senla.training.accounting_system.dto.user;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
