package com.fisa.backend.service;

import com.fisa.backend.model.Member;
import com.fisa.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberRestService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberRestService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

    public Optional<Member> findByMemberId(Long memberId) {
        return memberRepository.findAllById(memberId);
    }

}
