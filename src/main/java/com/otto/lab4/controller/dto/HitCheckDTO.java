package com.otto.lab4.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.time.Instant;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HitCheckDTO {
    private Double x;
    private Double y;

    @Positive
    private Double r;
    private Instant callingDate;
    private Long executionTime;
    private Boolean hitStatus;
}
