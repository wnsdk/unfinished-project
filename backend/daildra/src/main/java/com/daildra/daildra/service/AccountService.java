package com.daildra.daildra.service;

import com.daildra.daildra.data.dto.LogInResultDto;
import com.daildra.daildra.data.dto.SignUpResultDto;

public interface AccountService {

    SignUpResultDto signUp(String userId, String userPassword, String userNickname, String userEmail, String role);

    LogInResultDto logIn(String userId, String userPassword) throws RuntimeException;

}
