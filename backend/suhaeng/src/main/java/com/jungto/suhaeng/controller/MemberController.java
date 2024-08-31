package com.jungto.suhaeng.controller;

import com.jungto.suhaeng.domain.dto.request.MemberSignUpReqDto;
import com.jungto.suhaeng.domain.entity.Member;
import com.jungto.suhaeng.repository.MemberRepository;
import com.jungto.suhaeng.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "member", description = "멤버 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "멤버 전체 조회")
    @GetMapping
    public ResponseEntity<List<Member>> getMemberList(){

        return ResponseEntity.ok(memberService.getMemberList());
    }

    @Operation(summary = "멤버 조회")
    @GetMapping("/{memberId}")
    public ResponseEntity<Optional<Member>> getMember(@RequestParam Long memberId){
        return ResponseEntity.ok(memberService.getMember(memberId));
    }

    @Operation(summary = "회원 가입")
    @PostMapping
    public ResponseEntity<String> signUpMember(@RequestBody MemberSignUpReqDto memberSignUpReqDto){

//        memberRepository.save()
        return ResponseEntity.ok("회원가입!");
    }
}
