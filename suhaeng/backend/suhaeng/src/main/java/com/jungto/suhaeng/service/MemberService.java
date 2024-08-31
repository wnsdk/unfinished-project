package com.jungto.suhaeng.service;

import com.jungto.suhaeng.domain.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    List<Member> getMemberList();

    Optional<Member> getMember(Long memberId);
}
