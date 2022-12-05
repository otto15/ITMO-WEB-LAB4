package com.otto.lab4.service.command;

import com.otto.lab4.controller.dto.HitCheckDTO;
import com.otto.lab4.domain.HitCheck;
import lombok.Value;

@Value
public class CheckAndSaveDotCommand {
    HitCheckDTO hitCheck;
    Integer userId;
}
