package com.fisa.backend.controller;

import com.fisa.backend.model.Member;
import com.fisa.backend.service.MemberRestService;
import com.fisa.backend.service.dto.LoginRequest;
import com.fisa.backend.service.dto.MemberResponse;
import com.fisa.backend.service.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberRestController {

    private final MemberRestService memberRestService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Member> memberOpt = memberRestService.findByEmail(loginRequest.getEmail());

        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            if (member.getPassword().equals(loginRequest.getPassword())) {
                MemberResponse memberResponse = new MemberResponse(member.getId(), member.getName(), member.getEmail(), member.getPassword());
                // 리다이렉트할 URI
                URI location = URI.create("/todos/" + member.getId());

                // Location 헤더에 리다이렉트 URI 포함, 그리고 사용자 정보 JSON 반환
                return ResponseEntity.created(location).body(memberResponse);
            }
        }

        // 로그인 실패 시 401 Unauthorized 반환
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("이메일 또는 비밀번호가 잘못되었습니다.");

    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        // 사용자 존재 여부 확인
        if (memberRestService.findByEmail(signupRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("이미 존재하는 이메일입니다.");
        }

        Member member = new Member(signupRequest.getName(), signupRequest.getEmail(), signupRequest.getPassword());
        memberRestService.save(member);

        MemberResponse memberResponse = MemberResponse.from(member);

        URI location = URI.create("/login");

        return ResponseEntity.created(location).body(memberResponse);
    }

}
