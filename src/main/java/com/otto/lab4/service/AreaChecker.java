package com.otto.lab4.service;

import com.otto.lab4.domain.HitCheck;

import java.io.Serializable;

public interface AreaChecker extends Serializable {
    boolean checkIfInArea(HitCheck value);
}
