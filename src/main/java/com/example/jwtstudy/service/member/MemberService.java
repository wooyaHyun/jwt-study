package com.example.jwtstudy.service.member;

import com.example.jwtstudy.domain.member.MemberRepository;
import com.example.jwtstudy.dto.member.MemberJoinRequestDto;
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
}
