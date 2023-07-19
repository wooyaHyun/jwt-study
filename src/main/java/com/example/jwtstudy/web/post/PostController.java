package com.example.jwtstudy.web.post;


import com.example.jwtstudy.dto.member.MemberJoinRequestDto;
import com.example.jwtstudy.dto.member.MemberResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @GetMapping("/posts")
    public ResponseEntity<Void> posts() {
        return ResponseEntity.ok().build();
    }
}
