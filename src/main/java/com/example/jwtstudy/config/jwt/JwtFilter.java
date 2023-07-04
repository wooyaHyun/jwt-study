package com.example.jwtstudy.config.jwt;

import com.example.jwtstudy.constant.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;


    /**
     * jwt 토큰의 인증정보를 SecurityContext에 저장하는 역할 수행
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Request Header 에서 토큰을 꺼냄

        log.info("::::::jwt Filter Check::::");
        String jwt = resolveToken(request);

        log.info("jwt ::: {}", jwt);
        log.info("StringUtils.hasText(jwt) ::: {}", StringUtils.hasText(jwt));
        log.info("tokenProvider.validateToken(jwt) ::: {}", tokenProvider.validateToken(jwt));

        // 2. validateToken 으로 토큰 유효성 검사
        // 정상 토큰이면 해당 토큰으로 Authentication 을 가져와서 SecurityContext 에 저장
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Security Context에 '{}' 인증 정보를 저장했습니다.", authentication.getName());
        }

        filterChain.doFilter(request, response);  // 다음 Filter를 실행하기 위한 코드. 마지막 필터라면 필터 실행 후 리소스를 반환한다.
    }

    /**
     * Request Header에서 토큰 정보를 꺼내오기 위한 resolveToken 메서드
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constants.AUTH_HEADER);
        log.info("bearerToken:::: {}", bearerToken);


        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.TOKEN_TYPE)) {
            return bearerToken.substring(Constants.TOKEN_TYPE.length()).trim();
            //return bearerToken.substring(7);
        }
        return null;
    }
}
