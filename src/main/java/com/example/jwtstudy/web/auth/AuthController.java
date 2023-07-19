package com.example.jwtstudy.web.auth;

import com.example.jwtstudy.constant.Constants;
import com.example.jwtstudy.dto.TokenDto;
import com.example.jwtstudy.dto.member.MemberJoinRequestDto;
import com.example.jwtstudy.dto.member.MemberResponseDto;
import com.example.jwtstudy.dto.member.TokenRequestDto;
import com.example.jwtstudy.service.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName : com.example.jwtstudy.web.auth
 * fileName : AuthController
 * author : SHW
 * date : 2023-07-02
 * description :
 * ===========================================================
 * DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2023-07-02   SHW     최초 생성
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberJoinRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberJoinRequestDto memberRequestDto, HttpServletResponse response) {

        TokenDto token = authService.login(memberRequestDto);

        /*Cookie cookie = new Cookie("refresh", token.getRefreshToken().toString());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);*/

        return ResponseEntity.ok(token);
    }

    @PostMapping("/auth/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
