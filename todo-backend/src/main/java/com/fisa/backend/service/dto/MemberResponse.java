package com.fisa.backend.service.dto;

import com.fisa.backend.model.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

    private Long id;
    private String name;
    private String email;
    private String password;

    @Builder
    public MemberResponse(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .password(member.getPassword())
                .build();
    }
}
