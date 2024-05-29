package com.jungto.suhaeng.domain.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpReqDto {
    private String memberName;
    private String email;
    private String profileUrl;
}
