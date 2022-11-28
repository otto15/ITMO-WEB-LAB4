package com.otto.lab4.controller;

import com.otto.lab4.domain.HitCheck;
import com.otto.lab4.security.service.UserDetailsImpl;
import com.otto.lab4.service.AreaChecker;
import com.otto.lab4.service.HitCheckService;
import com.otto.lab4.service.command.CheckAndSaveDotCommand;
import com.otto.lab4.service.command.GetDotsForUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HitCheckController {

    private final HitCheckService hitCheckService;


    @Autowired
    public HitCheckController(HitCheckService hitCheckService) {
        this.hitCheckService = hitCheckService;
    }

    @GetMapping("/hit-checks")
    public List<HitCheck> getAllHitChecks(@RequestParam Double radius, @AuthenticationPrincipal UserDetailsImpl user) {
        return hitCheckService.getDotsForUser(new GetDotsForUserCommand(
                user.getId(), radius
        ));
    }

    @PostMapping("/hit-check")
    public void processHitCheck(HitCheck hitCheck, @AuthenticationPrincipal UserDetailsImpl user) {
        hitCheckService.checkAndSaveHitCheck(new CheckAndSaveDotCommand(hitCheck, user.getId()));
    }

    @DeleteMapping("/hit-check")
    public void deleteHitCheck(@AuthenticationPrincipal UserDetailsImpl user) {

    }

}
