package com.otto.lab4.repository;

import com.otto.lab4.domain.AuditedMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditedMethodRepository extends JpaRepository<AuditedMethod, Integer> {
}
