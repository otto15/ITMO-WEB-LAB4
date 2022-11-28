package com.otto.lab4.service;

import com.otto.lab4.domain.HitCheck;
import com.otto.lab4.repository.HitCheckRepository;
import com.otto.lab4.service.command.CheckAndSaveDotCommand;
import com.otto.lab4.service.command.GetDotsForUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HitCheckServiceImpl implements HitCheckService {

    private final HitCheckRepository hitCheckRepository;
    private final AreaChecker areaChecker;

    @Autowired
    public HitCheckServiceImpl(AreaChecker areaChecker, HitCheckRepository hitCheckRepository) {
        this.areaChecker = areaChecker;
        this.hitCheckRepository = hitCheckRepository;
    }
    @Override
    public List<HitCheck> getDotsForUser(GetDotsForUserCommand getDotsForUserCommand) {
        return hitCheckRepository.findAllByUserId(getDotsForUserCommand.getUserId());
    }

    @Override
    public void checkAndSaveHitCheck(CheckAndSaveDotCommand checkAndSaveDotCommand) {

    }

    @Override
    public void deleteDot(Integer userId) {

    }
}
