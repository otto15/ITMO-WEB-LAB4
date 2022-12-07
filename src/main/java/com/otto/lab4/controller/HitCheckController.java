package com.otto.lab4.controller;

import com.otto.lab4.controller.dto.HitCheckDTO;
import com.otto.lab4.domain.HitCheck;
import com.otto.lab4.security.service.BearerUser;
import com.otto.lab4.security.service.UserDetailsImpl;
import com.otto.lab4.service.HitCheckService;
import com.otto.lab4.service.command.CheckAndSaveDotCommand;
import com.otto.lab4.service.command.GetDotsForUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class HitCheckController {

    private final HitCheckService hitCheckService;


    @Autowired
    public HitCheckController(HitCheckService hitCheckService) {
        this.hitCheckService = hitCheckService;
    }

    @GetMapping("/hit-checks")
    public List<HitCheckDTO> getAllHitChecks(@RequestParam(required = false) @Positive Double radius,
                                          @AuthenticationPrincipal BearerUser user) {
        return hitCheckService.getDotsForUser(new GetDotsForUserCommand(
                user.getUserId(), radius
        ));
    }

    @PostMapping("/hit-check")
    public void processHitCheck(@Valid @RequestBody HitCheckDTO hitCheck, @AuthenticationPrincipal BearerUser user) {
        hitCheckService.checkAndSaveHitCheck(new CheckAndSaveDotCommand(hitCheck, user.getUserId()));

    }

    @DeleteMapping("/hit-checks")
    public void deleteHitCheck(@AuthenticationPrincipal BearerUser user) {
        hitCheckService.deleteAllHitChecksByUser(user.getUserId());
    }

}
