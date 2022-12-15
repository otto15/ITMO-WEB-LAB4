package com.otto.lab4.domain;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class HitCheck {

    private Integer userId;
    private Integer id;
    private Double x;
    private Double y;
    private Double r;

    private Instant callingDate;
    private Long executionTime;
    private Boolean hitStatus;
}
