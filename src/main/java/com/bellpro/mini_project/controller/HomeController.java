package com.bellpro.mini_project.controller;

import com.bellpro.mini_project.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @GetMapping("/")
    public String Home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null){
            model.addAttribute("username", "guest");        // 손님으로 변수 설정
        } else {
            model.addAttribute("username", userDetails.getUsername());  // 로그인한 사용자로 변수 설정
        }

        return "index"; // 메인 페이지로 이동
    }
}