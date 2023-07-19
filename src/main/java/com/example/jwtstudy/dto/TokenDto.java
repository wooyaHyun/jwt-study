package com.example.jwtstudy.dto;

import lombok.*;

/**
 * packageName : com.example.jwtstudy.dto
 * fileName : TokenDto
 * author : SHW
 * date : 2023-06-30
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-30   SHW     최초 생성
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TokenDto {


    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
}
