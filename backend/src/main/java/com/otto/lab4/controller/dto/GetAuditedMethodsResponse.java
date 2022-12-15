package com.otto.lab4.controller.dto;

import lombok.Value;

import java.util.List;

@Value
public class GetAuditedMethodsResponse {
    List<AuditedMethodDTO> methods;
}
