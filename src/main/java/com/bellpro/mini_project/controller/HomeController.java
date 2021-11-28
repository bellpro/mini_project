package com.bellpro.mini_project.controller;

import com.bellpro.mini_project.repository.BoardRepository;
import com.bellpro.mini_project.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final BoardRepository boardRepository;


    @GetMapping("/")
    public String Home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null){
            model.addAttribute("username", "guest");        // 손님으로 변수 설정
        } else {
            model.addAttribute("username", userDetails.getUsername());  // 로그인한 사용자로 변수 설정
        }
        model.addAttribute("boards", boardRepository.findByOrderByCreatedAtDesc());  // 게시글 전체 리스트
        return "index"; // 메인 페이지로 이동
    }

    //게시글 작성 페이지
    @GetMapping("/board/write")
    public String moveToNewPost(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            return "로그인 필요";        // 로그인 안하면 에러 처리
        }
        model.addAttribute("username", userDetails.getUsername());  // 로그인 사용자 아이디 전달
        return "/board/write";   // 게시글 작성 페이지로 이동
    }
}