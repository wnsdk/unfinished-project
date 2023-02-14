package com.daildra.daildra.controller.account;

import com.daildra.daildra.data.dto.LogInResultDto;
import com.daildra.daildra.service.AccountService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("account")
@RestController
public class AccountController {
    private final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("log-in")
    public ResponseEntity<LogInResultDto> logIn(@ApiParam(value = "user", required = true) @RequestBody Map<String, String> user) throws RuntimeException{
        String userId = user.get("userId");
        LOGGER.info("[logIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", userId);
        LogInResultDto logInResultDto = accountService.logIn(userId, user.get("userPassword"));

        if (logInResultDto.getCode() == 0) {
            LOGGER.info("[logIn] 정상적으로 로그인되었습니다. id : {}, token : {}", userId, logInResultDto.getToken());
            return new ResponseEntity<LogInResultDto>(logInResultDto, HttpStatus.OK);
        }

        LOGGER.info("[logIn] 정상적으로 로그인되지 않았습니다. id : {}", userId);
        return new ResponseEntity<LogInResultDto>(logInResultDto, HttpStatus.UNAUTHORIZED);
    }
}
