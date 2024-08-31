package com.jungto.suhaeng.repository;

import com.jungto.suhaeng.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String username);
}
