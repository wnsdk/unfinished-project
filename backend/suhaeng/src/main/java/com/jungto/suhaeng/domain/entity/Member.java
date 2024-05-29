package com.jungto.suhaeng.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role", nullable = false)
//    @Enumerated(EnumType.STRING)
    private String role;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "profile_url")
    private String profileUrl;
}
