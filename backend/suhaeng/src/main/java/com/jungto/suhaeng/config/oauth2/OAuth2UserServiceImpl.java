package com.jungto.suhaeng.config.oauth2;

import com.jungto.suhaeng.domain.entity.Member;
import com.jungto.suhaeng.domain.enums.MemberRole;
import com.jungto.suhaeng.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        //유저 정보(attributes) 가져오기
        Map<String, Object> oAuth2UserAttributes = super.loadUser(userRequest).getAttributes();

        //registrationId(third-party id) 가져오기
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //userNameAttributeName 가져오기
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        //유저 정보 생성
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(registrationId, oAuth2UserAttributes);

        //존재하지 않는 멤버라면 회원가입하기
        Member member = memberRepository.findByEmail(oAuth2UserInfo.email());

        if (member == null) {
            member = signUpUser(oAuth2UserInfo);
        }

        //OAuth2User 반환
        return new PrincipalDetails(member, oAuth2UserAttributes, userNameAttributeName);
    }

    /**
     * 회원가입
     *
     * @return DB에 회원을 저장한 결과
     */
    private Member signUpUser(OAuth2UserInfo oAuth2UserInfo) {
        Member savedMember = Member.builder()
                .memberName(oAuth2UserInfo.name())
                .email(oAuth2UserInfo.email())
                .role(MemberRole.ROLE_USER)
                .profileUrl(oAuth2UserInfo.profile())
                .status("ACTIVE")
                .build();

        return memberRepository.saveAndFlush(savedMember);
    }
}
