package com.otto.lab4.service;

import com.otto.lab4.controller.dto.JwtResponse;
import com.otto.lab4.controller.dto.UserCredentialsDTO;
import com.otto.lab4.security.service.UserDetailsImpl;

public interface UserService {

    JwtResponse login(UserCredentialsDTO userCredentialsDTO);
    void register(UserCredentialsDTO userCredentialsDTO);
    JwtResponse refresh(String refreshToken, UserDetailsImpl userDetails);

}
