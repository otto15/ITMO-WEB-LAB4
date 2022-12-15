package com.otto.lab4.repository;

public interface RefreshTokenRepository {
    boolean existByToken(String token);
    Integer save(String token);
}