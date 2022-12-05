package com.otto.lab4.controller.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenDTO {

    @NotBlank
    private String refreshToken;

}
