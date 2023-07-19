package com.example.jwtstudy.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * packageName : com.example.jwtstudy.constant
 * fileName : Constants
 * author : SHW
 * date : 2023-06-30
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-30   SHW     최초 생성
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    public static final String TOKEN_TYPE = "Bearer ";
    public static final String AUTH_HEADER = "Authorization";
    public static final String AUTHORITIES_KEY = "auth";
//    public static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 2;            // 2분
//    public static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 5;  // 5분

}
