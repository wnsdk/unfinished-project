package com.jungto.suhaeng.domain;

import jakarta.persistence.Embeddable;

//복합키
@Embeddable
public class LikesId {
    private Long memberId;
    private Long feedId;
}
