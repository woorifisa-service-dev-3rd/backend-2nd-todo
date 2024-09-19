package com.fisa.backend.service.dto;

import lombok.Getter;

@Getter
public class SignupRequest {

    private String name;
    private String email;
    private String password;

    public SignupRequest() {
    }

    public SignupRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
