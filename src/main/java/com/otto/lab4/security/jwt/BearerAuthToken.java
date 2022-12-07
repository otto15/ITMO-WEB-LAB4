package com.otto.lab4.security.jwt;

import com.otto.lab4.security.service.BearerUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import java.util.Collections;

public class BearerAuthToken extends AbstractAuthenticationToken {

    private final BearerUser user;

    public BearerAuthToken(BearerUser user) {
        super(Collections.emptyList());
        this.user = user;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }
}
