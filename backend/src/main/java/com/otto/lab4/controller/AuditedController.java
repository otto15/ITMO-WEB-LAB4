package com.otto.lab4.controller;

import com.otto.lab4.controller.dto.AuditedMethodDTO;
import com.otto.lab4.controller.dto.GetAuditedMethodsResponse;
import com.otto.lab4.repository.AuditedMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class AuditedController {

    @Autowired
    private AuditedMethodRepository auditedMethodRepository;

    @GetMapping("/methods")
    public GetAuditedMethodsResponse getAuditedMethods() {
        return new GetAuditedMethodsResponse(
                auditedMethodRepository.findAll()
                        .stream()
                        .map(
                                method -> new AuditedMethodDTO(method.getMethodSignature(), method.getUserId(),
                                        method.getCallingDate())
                        ).collect(Collectors.toList())
        );
    }

}
