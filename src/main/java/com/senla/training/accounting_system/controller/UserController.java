package com.senla.training.accounting_system.controller;

import com.senla.training.accounting_system.dto.user.UserDto;
import com.senla.training.accounting_system.mapper.UserMapper;
import com.senla.training.accounting_system.model.User;
import com.senla.training.accounting_system.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/users/")
public class UserController {

    private final UserService userService;

    private UserMapper userMapper;

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") @Positive Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(userMapper.entityToDto(user));
    }
}
