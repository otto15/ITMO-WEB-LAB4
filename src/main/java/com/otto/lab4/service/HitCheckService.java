package com.otto.lab4.service;

import com.otto.lab4.controller.dto.HitCheckDTO;
import com.otto.lab4.domain.HitCheck;
import com.otto.lab4.service.command.CheckAndSaveDotCommand;
import com.otto.lab4.service.command.GetDotsForUserCommand;

import java.util.List;

public interface HitCheckService {

    List<HitCheckDTO> getDotsForUser(GetDotsForUserCommand getDotsForUserCommand);
    void checkAndSaveHitCheck(CheckAndSaveDotCommand checkAndSaveDotCommand);
    void deleteAllHitChecksByUser(Integer userId);

}
