package com.otto.lab4.repository;

import com.otto.lab4.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByName(String name);
    boolean existsByName(String name);
    
}
