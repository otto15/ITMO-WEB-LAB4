package com.otto.lab4.controller;

import com.otto.lab4.controller.dto.JwtResponse;
import com.otto.lab4.controller.dto.Message;
import com.otto.lab4.controller.dto.RefreshTokenDTO;
import com.otto.lab4.controller.dto.UserCredentialsDTO;
import com.otto.lab4.exception.GlobalException;
import com.otto.lab4.security.service.UserDetailsImpl;
import com.otto.lab4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public JwtResponse login(@Valid UserCredentialsDTO userCredentials) {
        return userService.login(userCredentials);
    }

    @PostMapping("/register")
    public void register(UserCredentialsDTO userCredentials) {
        userService.register(userCredentials);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(RefreshTokenDTO refreshTokenDTO, @AuthenticationPrincipal UserDetailsImpl user) {
        return userService.refresh(refreshTokenDTO.getRefreshToken(), user);
    }

    @ExceptionHandler
    public ResponseEntity<Message> handle(GlobalException e) {
        return new ResponseEntity<>(new Message(e.getMessage()), e.getStatus());
    }

}
