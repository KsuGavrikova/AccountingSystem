package com.senla.training.accounting_system.dto.user;

import lombok.Data;

@Data
public class LoginTokenDto {
    private String username;
    private String token;
}
