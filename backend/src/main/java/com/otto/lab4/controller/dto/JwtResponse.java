package com.otto.lab4.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class JwtResponse {

    private String type;
    private String accessToken;
    private String refreshToken;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer userId;

}
