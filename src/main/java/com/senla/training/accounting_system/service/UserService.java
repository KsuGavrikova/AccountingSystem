package com.senla.training.accounting_system.service;

import com.senla.training.accounting_system.dto.user.UserDto;
import com.senla.training.accounting_system.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    UserDto update(UserDto userDto);
}
