package com.jungto.suhaeng.service;

import com.jungto.suhaeng.domain.entity.Member;
import com.jungto.suhaeng.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;


    @Override
    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> getMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
