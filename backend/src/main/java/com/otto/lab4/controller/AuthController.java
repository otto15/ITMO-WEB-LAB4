package com.otto.lab4.controller;

import com.otto.lab4.audited.Audited;
import com.otto.lab4.controller.dto.JwtResponse;
import com.otto.lab4.controller.dto.RefreshTokenDTO;
import com.otto.lab4.controller.dto.UserCredentialsDTO;
import com.otto.lab4.service.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody UserCredentialsDTO userCredentials) {
        return userService.login(userCredentials);
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody UserCredentialsDTO userCredentials) {
        userService.register(userCredentials);
    }

    @Audited
    @PostMapping("/refresh")
    public JwtResponse refresh(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
        return userService.refresh(refreshTokenDTO.getRefreshToken());
    }

    @PostMapping("/logout")
    public void logout(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
        userService.logout(refreshTokenDTO.getRefreshToken());
    }

}
