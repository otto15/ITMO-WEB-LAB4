package com.otto.lab4.service;

import com.otto.lab4.controller.dto.JwtResponse;
import com.otto.lab4.controller.dto.UserCredentialsDTO;
import com.otto.lab4.domain.AppUser;
import com.otto.lab4.exception.InvalidRefreshTokenException;
import com.otto.lab4.exception.UserAlreadyExistsException;
import com.otto.lab4.repository.UserRepository;
import com.otto.lab4.security.jwt.JwtUtils;
import com.otto.lab4.security.service.BearerUser;
import com.otto.lab4.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public JwtResponse login(UserCredentialsDTO userCredentialsDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentialsDTO.getUsername(), userCredentialsDTO.getPassword())
        );
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        BearerUser authedUser = new BearerUser(userDetails.getId());
        String accessToken = jwtUtils.generateAccessTokenFromUsername(authedUser);
        String refreshToken = jwtUtils.generateRefreshTokenFromUsername(authedUser);

        return JwtResponse.builder()
                .userId(userDetails.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .type(JwtUtils.TOKEN_TYPE)
                .build();
    }

    @Override
    public void register(UserCredentialsDTO userCredentialsDTO) {
        if (userRepository.existsByName(userCredentialsDTO.getUsername())) {
            throw new UserAlreadyExistsException();
        }
        AppUser user = AppUser.builder()
                .name(userCredentialsDTO.getUsername())
                .password(encoder.encode(userCredentialsDTO.getPassword()))
                .build();
        userRepository.save(user);
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        if (jwtUtils.validateRefreshJwtToken(refreshToken)) {

            String newAccessToken = jwtUtils.generateAccessTokenFromUsername(jwtUtils
                    .getUsernameFromRefreshJwtToken(refreshToken));
            return JwtResponse.builder()
                    .type(JwtUtils.TOKEN_TYPE)
                    .refreshToken(refreshToken)
                    .accessToken(newAccessToken)
                    .build();
        }
        throw new InvalidRefreshTokenException();
    }
}
