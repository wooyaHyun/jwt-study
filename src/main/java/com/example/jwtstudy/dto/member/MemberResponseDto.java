package com.example.jwtstudy.dto.member;

import com.example.jwtstudy.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName : com.example.jwtstudy.dto.member
 * fileName : MemberResponseDto
 * author : SHW
 * date : 2023-07-01
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-07-01   SHW     최초 생성
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private String username;
    

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getUsername());
    }
}
