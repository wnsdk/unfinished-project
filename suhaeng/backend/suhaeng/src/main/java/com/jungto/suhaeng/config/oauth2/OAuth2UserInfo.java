package com.jungto.suhaeng.config.oauth2;

import com.jungto.suhaeng.domain.entity.Member;
import com.jungto.suhaeng.domain.enums.MemberRole;
import jakarta.security.auth.message.AuthException;
import lombok.Builder;

import java.util.Map;

@Builder
public record OAuth2UserInfo(
        String name,
        String email,
        String profile
) {

    public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes) {
        return switch (registrationId) { // registration id 별로 userInfo 생성
            case "google" -> ofGoogle(attributes);
//                default -> throw new AuthException(ILLEGAL_REGISTRATION_ID);
            default -> throw new IllegalArgumentException("Invalid Provider Type.");
        };
    }

    private static OAuth2UserInfo ofGoogle(Map<String, Object> attributes) {
        return OAuth2UserInfo.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profile((String) attributes.get("picture"))
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .memberName(name)
                .email(email)
                .profileUrl(profile)
                .role(MemberRole.ROLE_USER)
                .build();
    }
}
