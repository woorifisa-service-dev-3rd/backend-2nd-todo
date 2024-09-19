package com.fisa.backend.controller;

import com.fisa.backend.model.Member;
import com.fisa.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 로그인 폼을 보여주는 메서드
    @GetMapping("/login")
    public String showLoginForm() {
        return "login/login";
    }

    // 로그인 처리 메서드
    @PostMapping("/login")
    public String handleLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            Model model) {

        Optional<Member> memberOpt = memberService.findByEmail(email);

        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            if (member.getPassword().equals(password)) {
                // 로그인 성공 후 홈 페이지로 리다이렉트
                session.setAttribute("loggedInMember", member);
                return "redirect:/todos/"+ member.getId();
            }
        }

        // 로그인 실패
        model.addAttribute("error", true);
        return "login/login";
    }

    // 회원가입 폼을 보여주는 메서드
    @GetMapping("/signup")
    public String showSignupForm() {
        return "login/signup";
    }

    // 회원가입 처리 메서드
    @PostMapping("/signup")
    public String handleSignup(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {

        // 사용자 존재 여부 확인
        if (memberService.findByEmail(email).isPresent()) {
            model.addAttribute("error", true);
            return "login/signup";
        }

        // 사용자 저장
        Member member = Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
        memberService.save(member);

        // 회원가입 성공 후 로그인 페이지로 리다이렉트
        return "redirect:/login";
    }
}
