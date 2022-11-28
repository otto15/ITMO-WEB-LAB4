package com.otto.lab4.domain;

import lombok.Data;

import java.time.Instant;

@Data
public class HitCheck {
    private Integer userId;
    private Integer id;
    private Double x;
    private Double y;
    private Double r;
    private Instant callingDate;
    private Long executionTime;
    private boolean hitStatus;
}
