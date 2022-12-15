package com.otto.lab4.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name="audited_methods")
@Getter
@Setter
@NoArgsConstructor
public class AuditedMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String methodSignature;

    private Instant callingDate = Instant.now();

    public AuditedMethod(Integer userId, String methodSignature) {
        this.userId = userId;
        this.methodSignature = methodSignature;
    }

}
