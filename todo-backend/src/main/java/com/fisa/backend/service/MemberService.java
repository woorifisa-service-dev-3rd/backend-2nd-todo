package com.fisa.backend.service;


import com.fisa.backend.model.Member;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findByEmail(String email);
    void save(Member member);

    Optional<Member> findByMemberId(Long id);

}
