package com.otto.lab4.service;

import com.otto.lab4.controller.dto.JwtResponse;
import com.otto.lab4.controller.dto.UserCredentialsDTO;
import com.otto.lab4.domain.AppUser;
import com.otto.lab4.exception.InvalidRefreshTokenException;
import com.otto.lab4.exception.UserAlreadyExistsException;
import com.otto.lab4.repository.RefreshTokenRepository;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                           UserRepository userRepository, PasswordEncoder passwordEncoder,
                           RefreshTokenRepository refreshTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public JwtResponse login(UserCredentialsDTO userCredentialsDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentialsDTO.getUsername(), userCredentialsDTO.getPassword())
        );
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        String accessToken = jwtUtils.generateAccessTokenFromUserId(userDetails.getId());
        String refreshToken = jwtUtils.generateRefreshTokenFromUserId(userDetails.getId());

        return JwtResponse.builder()
                .userId(userDetails.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .type(JwtUtils.TOKEN_TYPE)
                .build();
    }

    @Override
    public void register(UserCredentialsDTO userCredentialsDTO) {
        AppUser user = AppUser.builder()
                .name(userCredentialsDTO.getUsername())
                .password(encoder.encode(userCredentialsDTO.getPassword()))
                .build();
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        if (jwtUtils.validateRefreshJwtToken(refreshToken) && !refreshTokenRepository.existByToken(refreshToken)) {

            String newAccessToken = jwtUtils.generateAccessTokenFromUserId(jwtUtils
                    .getUserIdFromRefreshJwtToken(refreshToken));
            return JwtResponse.builder()
                    .type(JwtUtils.TOKEN_TYPE)
                    .refreshToken(refreshToken)
                    .accessToken(newAccessToken)
                    .build();
        }
        throw new InvalidRefreshTokenException();
    }

    @Override
    public void logout(String refreshToken) {
        if (jwtUtils.validateRefreshJwtToken(refreshToken)) {
            refreshTokenRepository.save(refreshToken);
        }
    }
}
