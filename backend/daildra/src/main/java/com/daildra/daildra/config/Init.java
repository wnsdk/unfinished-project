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
    private static String defaultImg = "https://d1v10kml6l14kq.cloudfront.net/default.jpg";

    @PostConstruct
    protected void init() {
        LOGGER.info("[init] admin 유저생성 시작");
        User user = User.builder()
                .userUid("1")
                .userId("daildra")
                .userEmail("daildra@gmail.com")
                .userPassword(passwordEncoder.encode("epdlfwmfk9!"))
                .userRoles(Collections.singletonList("ROLE_ADMIN"))
                .userNickname("데일드라")

                .build();
        LOGGER.info("---------------------관리자계정 : {}", user.toString());
        userRepository.save(user);
        LOGGER.info("[init] admin 유저생성 완료 id : {}", user.getUserEmail());
    }
}