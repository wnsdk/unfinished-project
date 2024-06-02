package com.jungto.suhaeng.domain.enums;

public enum MemberRole {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_GUEST("ROLE_GUEST");

    private final String value;  // 각 상태를 설명하는 문자열 값

    MemberRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
