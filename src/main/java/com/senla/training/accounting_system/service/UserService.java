package com.senla.training.accountingSystem.service;

import com.senla.training.accountingSystem.dto.UserDto;
import com.senla.training.accountingSystem.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    UserDto update(UserDto userDto);
}
