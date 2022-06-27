package com.senla.training.accounting_system.controller;

import com.senla.training.accounting_system.dto.user.AuthenticationRequestDto;
import com.senla.training.accounting_system.dto.user.LoginTokenDto;
import com.senla.training.accounting_system.dto.user.RegistrationUserDto;
import com.senla.training.accounting_system.mapper.RegistrationUserMapper;
import com.senla.training.accounting_system.model.User;
import com.senla.training.accounting_system.security.jwt.JwtTokenProvider;
import com.senla.training.accounting_system.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/auth/")
@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RegistrationUserMapper registrationUserMapper;
    private final UserService userService;


    @PostMapping("login")
    public ResponseEntity<LoginTokenDto> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                log.error("IN login - User with username: " + username + " not found");
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());
            log.info("IN login - token was create");
            LoginTokenDto loginTokenDto = new LoginTokenDto();
            loginTokenDto.setUsername(username);
            loginTokenDto.setToken(token);

            return ResponseEntity.ok(loginTokenDto);
        } catch (AuthenticationException e) {
            log.error("IN login - Invalid username or password");
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationUserDto registrationUserDto) {
        String username = registrationUserDto.getUsername();
        if (userService.findByUsername(username) != null) {
            log.info("IN registration - User with name " + username + " already exists");
            return ResponseEntity.badRequest().body(" User with name " + username + " already exists ");
        }
        User userReg = userService.register(registrationUserMapper.dtoToEntity(registrationUserDto));
        registrationUserDto.setPassword(userReg.getPassword());

        log.info("IN registration -User was registration");
        return ResponseEntity.ok(registrationUserDto.getUsername());

    }
}
