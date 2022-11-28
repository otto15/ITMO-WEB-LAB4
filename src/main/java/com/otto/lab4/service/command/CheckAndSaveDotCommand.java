package com.otto.lab4.service.command;

import com.otto.lab4.domain.HitCheck;
import lombok.Value;

@Value
public class CheckAndSaveDotCommand {
    HitCheck hitCheck;
    Integer userId;
}
