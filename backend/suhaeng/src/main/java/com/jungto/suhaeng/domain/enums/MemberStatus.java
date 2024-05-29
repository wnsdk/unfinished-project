package com.jungto.suhaeng.domain.enums;

public enum MemberStatus {
    ACTIVE("ACTIVE"),        // 활성 상태: 로그인 가능하고 서비스 이용 가능
    INACTIVE("INACTIVE"),    // 비활성 상태: 일시적으로 비활성화된 상태
    SUSPENDED("SUSPENDED"),  // 정지 상태: 일시적으로 사용이 정지된 상태
    PENDING("PENDING"),      // 대기 상태: 가입 완료 또는 관리자 승인 대기 중
    DELETED("DELETED");      // 삭제 상태: 회원 정보가 영구적으로 삭제된 상태

    private final String value;  // 각 상태를 설명하는 문자열 값

    MemberStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
