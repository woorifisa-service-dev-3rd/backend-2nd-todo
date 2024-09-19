package com.fisa.backend.model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor // 기본 생성자를 제공
@Builder
public class Member extends BaseEntity {

    private String name;
    private String email;
    private String password;

    // 추가: 공용 생성자 추가 (선택적)
    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


}
