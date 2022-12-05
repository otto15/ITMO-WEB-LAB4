package com.otto.lab4.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    public static final String TOKEN_TYPE = "Bearer";
    @Value("${app.jwt.access.secret}")
    private String jwtAccessSecret;

    @Value("${app.jwt.refresh.secret}")
    private String jwtRefreshSecret;

    @Value("${app.jwt.access.expiration}")
    private int jwtAccessExpiration;

    @Value("${app.jwt.refresh.expiration}")
    private int jwtRefreshExpiration;

    private String generateTokenFromUsername(String username, String jwtSecret, int expiration) {
        Date currentDate = new Date();
        return Jwts.builder().setSubject(username).setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + expiration))
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateAccessTokenFromUsername(String username) {
        return generateTokenFromUsername(username, jwtAccessSecret, jwtAccessExpiration);
    }

    public String generateRefreshTokenFromUsername(String username) {
        return generateTokenFromUsername(username, jwtRefreshSecret, jwtRefreshExpiration);
    }

    private String getUsernameFromJwtToken(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String getUsernameFromAccessJwtToken(String token) {
        return getUsernameFromJwtToken(token, jwtAccessSecret);
    }

    public String getUsernameFromRefreshJwtToken(String token) {
        return getUsernameFromJwtToken(token, jwtRefreshSecret);
    }

    public boolean validateJwtToken(String authToken, String jwtSecret) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public boolean validateAccessJwtToken(String token) {
        return validateJwtToken(token, jwtAccessSecret);
    }

    public boolean validateRefreshJwtToken(String token) {
        return validateJwtToken(token, jwtRefreshSecret);
    }

}
