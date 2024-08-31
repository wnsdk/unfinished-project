package com.daildra.daildra.service.impl;

import com.daildra.daildra.config.security.JwtTokenProvider;
import com.daildra.daildra.data.dto.LogInResultDto;
import com.daildra.daildra.data.dto.SignUpResultDto;
import com.daildra.daildra.data.entity.User;
import com.daildra.daildra.data.repository.UserRepository;
import com.daildra.daildra.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
//@RequiredArgsConstructor
//@Transactional
public class AccountServiceImpl implements AccountService {

    private final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignUpResultDto signUp(String userId, String userPassword, String userNickname, String userEmail, String role) {

        LOGGER.info("[getSignUpResult] 회원가입 정보 전달");
        User user;
        if (role.equalsIgnoreCase("admin")) {
            user = User.builder()
                    .userId(userId)
                    .userPassword(passwordEncoder.encode(userPassword))
                    .userNickname(userNickname)
                    .userEmail(userEmail)
                    .userRoles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        }
        else {
            user = User.builder()
                    .userId(userId)
                    .userPassword(passwordEncoder.encode(userPassword))
                    .userNickname(userNickname)
                    .userEmail(userEmail)
                    .userRoles(Collections.singletonList("ROLE_BASIC"))
                    .build();
        }

        User savedUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = null;

        if (!savedUser.getUserNickname().isEmpty()) {
            LOGGER.info("[signUp] 정상 처리 완료");
            signUpResultDto = signUpResultDto.builder().success(true).msg("회원가입 성공").build();
        } else {
            LOGGER.info("[signUp] 실패 처리 완료");
            signUpResultDto = signUpResultDto.builder().success(false).msg("회원가입 실패").build();
        }

        return signUpResultDto;
    }

    @Override
    public LogInResultDto logIn(String userId, String password) throws RuntimeException {
        LOGGER.info("[getLogInResult] signDataHandler 로 회원 정보 요청");
        User user = userRepository.getByUserId(userId);
        LOGGER.info("[getLogInResult] Id : {}", userId);

        LOGGER.info("[getLogInResult] 패스워드 비교 수행"); // passwordEncoder를 통해 인코딩 안 된 값과 된 값을 비교
        if (!passwordEncoder.matches(password, user.getPassword())) {
            LOGGER.info("[getLogInResult] 패스워드 불일치");
            LOGGER.info("[getLogInResult] LogInResultDto 객체 생성");
            LogInResultDto logInResultDto = LogInResultDto.builder().success(false).code(1).msg("로그인 실패 : 패스워드 불일치").build();
            return logInResultDto;
        }

        LOGGER.info("[getLogInResult] 패스워드 일치");
        LOGGER.info("[getLogInResult] LogInResultDto 객체 생성");

        LogInResultDto logInResultDto = LogInResultDto.builder().token(jwtTokenProvider
                .createToken(String.valueOf(user.getUserUid()), user.getUserRoles())).build();

        LOGGER.info("[getLogInResult] LogInResultDto 객체에 값 주입");

        return logInResultDto;
    }

//    @Override
//    public void deleteUser(Long seq) {
//        LOGGER.info("[deleteUser] 회원정보 삭제 시작");
//        userRepository.deleteBySeq(seq);
//        LOGGER.info("[deleteUser] 회원정보 삭제 완료");
//    }
//
//    @Override
//    public void checkId(String id) throws Exception {
//        LOGGER.info("[checkId] 아이디 사용 확인");
//        Optional<User> user =userRepository.findById(id);
//        if (user.isPresent()) {
//            LOGGER.info("[checkId] 아이디 사용 불가 id : {}", user.get().getId());
//            throw new Exception();
//        }
//    }
}
