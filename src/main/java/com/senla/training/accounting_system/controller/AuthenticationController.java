package com.senla.training.accountingSystem.controller;

import com.senla.training.accountingSystem.dto.AuthenticationRequestDto;
import com.senla.training.accountingSystem.dto.RegistrationUserDto;
import com.senla.training.accountingSystem.model.User;
import com.senla.training.accountingSystem.security.jwt.JwtTokenProvider;
import com.senla.training.accountingSystem.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/auth/")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;


    @PostMapping("login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("registration")
    public ResponseEntity registration(@RequestBody RegistrationUserDto registrationUserDto) {
        try {
            String username = registrationUserDto.getUsername();
            if (userService.findByUsername(username) != null) {
                log.info("User with name " + username + " is empty");
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }

            User user = new User();
            user.setUsername(registrationUserDto.getUsername());
            user.setFirstName(registrationUserDto.getFirstName());
            user.setLastName(registrationUserDto.getLastName());
            user.setEmail(registrationUserDto.getEmail());
            user.setPassword(registrationUserDto.getPassword());
            User userReg = userService.register(user);
            registrationUserDto.setPassword(userReg.getPassword());

            return ResponseEntity.ok(registrationUserDto);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
