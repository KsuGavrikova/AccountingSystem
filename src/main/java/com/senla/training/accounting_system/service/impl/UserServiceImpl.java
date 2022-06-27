package com.senla.training.accounting_system.service.impl;

import com.senla.training.accounting_system.dto.user.UserDto;
import com.senla.training.accounting_system.enums.Status;
import com.senla.training.accounting_system.exeprion.NoEntityException;
import com.senla.training.accounting_system.mapper.UserMapper;
import com.senla.training.accounting_system.model.Role;
import com.senla.training.accounting_system.model.User;
import com.senla.training.accounting_system.repository.RoleRepository;
import com.senla.training.accounting_system.repository.UserRepository;
import com.senla.training.accounting_system.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("User with id ", id));
        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }

    @Override
    public UserDto update(UserDto userDto) {
        if (userRepository.existsById(userDto.getId())) {
            User user = userMapper.dtoToEntity(userDto);
            return userMapper.entityToDto(userRepository.save(user));
        } else {
            log.error("User {} not found", userDto);
            throw new NoEntityException();
        }
    }
}