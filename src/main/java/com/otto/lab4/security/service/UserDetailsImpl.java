package com.otto.lab4.security.service;

import com.otto.lab4.domain.AppUser;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Builder
public class UserDetailsImpl implements UserDetails {

    private Integer id;

    private String name;

    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(AppUser user) {
        return UserDetailsImpl.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
