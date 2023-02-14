package com.daildra.daildra.service;

import com.daildra.daildra.data.dto.LogInResultDto;
import com.daildra.daildra.data.dto.SignUpResultDto;

public interface AccountService {

    SignUpResultDto signUp(String id, String password, String name, String role);

    LogInResultDto logIn(String id, String password) throws RuntimeException;

}
