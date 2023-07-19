package com.example.jwtstudy.config.jwt;

import com.example.jwtstudy.constant.Constants;
import com.example.jwtstudy.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


/**
 * packageName : com.example.jwtstudy.config.jwt
 * fileName : TokenProvider
 * author : SHW
 * date : 2023-06-30
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-06-30   SHW     최초 생성
 */

@Slf4j
@Component
public class TokenProvider {

    private String secret;
    private Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret) {
        this.secret = secret;

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto createToken(Authentication authentication) {

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        log.info("now :::{}" , new Date(System.currentTimeMillis()));

         // 토큰 만료 시간
        Date accessTokenExpiresIn = new Date(now + Constants.ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiresIn = new Date(now + Constants.REFRESH_TOKEN_EXPIRE_TIME);

        log.info("accessTokenExpiresIn :::{}" , accessTokenExpiresIn);
        log.info("accessTokenExpiresIn :::{}" , accessTokenExpiresIn.getTime());

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())       // payload "sub": "name"                -> 토큰제목
                .claim(Constants.AUTHORITIES_KEY, authorities)  // payload "auth": "USER" -> 권한
                .setExpiration(accessTokenExpiresIn)        // payload "exp": 1516239022 (예시)      -> 토큰만료시간
                .signWith(key, SignatureAlgorithm.HS512)    // header "alg": "HS512"                -> 알고리즘 방식지정, 서명 및 토큰 검증에 사용
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenDto.builder()
                .grantType(Constants.TOKEN_TYPE)                            // BEARER
                .accessToken(accessToken)                                   // Access Token
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())       // Access Token 만료 시간
                .refreshToken(refreshToken)                                 // Refresh Token
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        //토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get(Constants.AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(Constants.AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);

    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            log.info(e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }


    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }



}
