package com.daildra.daildra.config;

import com.daildra.daildra.data.entity.User;
import com.daildra.daildra.data.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@RequiredArgsConstructor
@Component
public class Init {

    private final Logger LOGGER = LoggerFactory.getLogger(Init.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    protected void init() {
        LOGGER.info("[init] 테스트 유저 생성 시작");
        User user = User.builder()
                .userId("test")
                .userEmail("test@gmail.com")
                .userPassword(passwordEncoder.encode("test"))
                .userRoles(Collections.singletonList("ROLE_ADMIN"))
                .userNickname("테스트 유저")
                .build();

        userRepository.save(user);
        LOGGER.info("[init] 테스트 유저생성 완료 id : {}", user.getUserEmail());
    }
}