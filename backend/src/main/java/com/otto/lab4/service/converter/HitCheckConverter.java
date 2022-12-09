package com.otto.lab4.service.converter;

import com.otto.lab4.controller.dto.HitCheckDTO;
import com.otto.lab4.domain.HitCheck;
import org.springframework.stereotype.Service;

@Service
public class HitCheckConverter {

    public HitCheck map(HitCheckDTO hitCheckDTO) {
        return HitCheck.builder()
                .x(hitCheckDTO.getX())
                .y(hitCheckDTO.getY())
                .r(hitCheckDTO.getR())
                .build();
    }

    public HitCheckDTO unmap(HitCheck hitCheck) {
        return HitCheckDTO.builder()
                .x(hitCheck.getX())
                .y(hitCheck.getY())
                .r(hitCheck.getR())
                .callingDate(hitCheck.getCallingDate())
                .executionTime(hitCheck.getExecutionTime())
                .hitStatus(hitCheck.getHitStatus())
                .build();
    }

}
