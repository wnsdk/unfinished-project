package com.jungto.suhaeng.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.token.Token;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenService {

    private final RedisTemplate<String, String> redisTemplate;

    public void saveOrUpdate(String name, String refreshToken, String accessToken) {
        //TODO : redis에 토큰 저장
    }

    public String findByAccessTokenOrThrow(String accessToken) {
        return "";
    }

    public void updateToken(String reissueAccessToken, String token) {
    }
}
