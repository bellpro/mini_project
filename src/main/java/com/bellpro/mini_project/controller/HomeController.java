package com.bellpro.mini_project.controller;

import com.bellpro.mini_project.domain.Board;
import com.bellpro.mini_project.repository.BoardRepository;
import com.bellpro.mini_project.security.UserDetailsImpl;
import com.bellpro.mini_project.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;

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

    // 게시글 검색
    @GetMapping("/board/search")
    public String searchBoard(@RequestParam(value = "keyword") String keyword, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){    // GET 방식으로 넘어온 URL의 쿼리에서 받는 값을 설정
        List<Board> boardList = boardService.searchBoard(keyword);  // 키워드 검색
        model.addAttribute("boards", boardList);    // 키워드 검색 리스트를 전달
        if (userDetails == null){   // 로그인 여부
            model.addAttribute("username", "guest");
        } else {
            model.addAttribute("username", userDetails.getUsername());
        }

        return "index"; // 메인 페이지 이동
    }
}