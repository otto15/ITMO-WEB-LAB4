package com.otto.lab4.security;

import com.otto.lab4.security.jwt.JwtAuthEntryPoint;
import com.otto.lab4.security.jwt.JwtAuthFilter;
import com.otto.lab4.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.http.HttpMethod;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtAuthEntryPoint entryPoint;
    private final UserDetailsServiceImpl userDetailsService;

    private final JwtAuthFilter authFilter;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthEntryPoint entryPoint,
                          JwtAuthFilter authFilter) {
        this.userDetailsService = userDetailsService;
        this.entryPoint = entryPoint;
        this.authFilter = authFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);

        return authenticationProvider;
    }

    private static final String[] WHITE_LIST = {
            "/methods",
            "/api/auth/login",
            "/api/auth/register",
            "/api/auth/refresh",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(
                    request -> {
                        CorsConfiguration corsConfig = new CorsConfiguration().applyPermitDefaultValues();
                        corsConfig.setAllowedOrigins(List.of("http://localhost:8080"));
                        corsConfig.addAllowedMethod(HttpMethod.DELETE);
                        return corsConfig;
                    }
                )
                .and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(auth -> auth
                        .antMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
