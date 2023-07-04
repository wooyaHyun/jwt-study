package com.example.jwtstudy.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName : com.example.jwtstudy.dto.member
 * fileName : TokenRequestDto
 * author : SHW
 * date : 2023-07-03
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-07-03   SHW     최초 생성
 */

@Getter
@NoArgsConstructor
public class TokenRequestDto {
    private String accessToken;
    private String refreshToken;
}
