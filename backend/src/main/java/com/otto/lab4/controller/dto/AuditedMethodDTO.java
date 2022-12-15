package com.otto.lab4.controller.dto;

import lombok.Value;

import java.time.Instant;

@Value
public class AuditedMethodDTO {

    String methodSignature;
    Integer userId;
    Instant callingDate;

}
