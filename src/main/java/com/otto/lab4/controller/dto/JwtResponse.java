package com.otto.lab4.controller.dto;

import lombok.Builder;

@Builder
public class JwtResponse {

    private String type;
    private String accessToken;
    private String refreshToken;
    private Integer userId;

}
