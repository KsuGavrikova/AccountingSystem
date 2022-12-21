package com.senla.training.accounting_system.controller;

import com.senla.training.accounting_system.dto.user.AdminUserDto;
import com.senla.training.accounting_system.dto.user.UserDto;
import com.senla.training.accounting_system.mapper.AdminUserMapper;
import com.senla.training.accounting_system.model.User;
import com.senla.training.accounting_system.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/admin/")
public class AdminController {

    private final UserService userService;
    private final AdminUserMapper adminUserMapper;

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") @Positive Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AdminUserDto result = adminUserMapper.entityToDto(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<UserDto> updateBook(@PathVariable @Positive Long id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        UserDto updateBook = userService.update(userDto);
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }
}
