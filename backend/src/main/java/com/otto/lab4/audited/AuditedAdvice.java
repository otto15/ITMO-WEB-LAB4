package com.otto.lab4.audited;

import com.otto.lab4.domain.AuditedMethod;
import com.otto.lab4.repository.AuditedMethodRepository;
import com.otto.lab4.security.service.BearerUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditedAdvice {

    private final AuditedMethodRepository auditedMethodRepository;

    @Autowired
    public AuditedAdvice(AuditedMethodRepository auditedMethodRepository) {
        this.auditedMethodRepository = auditedMethodRepository;
    }

    @Before("@annotation(Audited)")
    private void saveInteraction(JoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Integer userId;
        try {
            BearerUser user = (BearerUser) auth.getPrincipal();
            userId = user.getUserId();
        } catch (ClassCastException e) {
            userId = -1;
        }

        auditedMethodRepository.save(new AuditedMethod(userId, methodName));

    }

}
