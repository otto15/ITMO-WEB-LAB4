package com.otto.lab4.service.command;

import lombok.Value;

@Value
public class GetDotsForUserCommand {
    Integer userId;
    Double radius;
}
