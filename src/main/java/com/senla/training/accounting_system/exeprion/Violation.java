package com.senla.training.accounting_system.exeprion;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Violation {

    private final String fieldName;

    private final String message;

}
