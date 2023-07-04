package com.example.jwtstudy.service.member;

import com.example.jwtstudy.domain.member.MemberRepository;
import com.example.jwtstudy.dto.member.MemberJoinRequestDto;
import com.example.jwtstudy.dto.member.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public String addMember(MemberJoinRequestDto requestDto) {
        return memberRepository.save(requestDto.toEntity(passwordEncoder)).getUsername();
    }

    public MemberResponseDto findMemberInfoById(Long memberId) {
        return memberRepository.findById(memberId)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저 정보가 없습니다."));
    }

    public MemberResponseDto findMemberInfoByEmail(String name) {
        return memberRepository.findByUsername(name)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new IllegalArgumentException("유저 정보가 없습니다."));
    }
}
