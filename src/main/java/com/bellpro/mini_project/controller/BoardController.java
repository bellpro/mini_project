package com.bellpro.mini_project.controller;

import com.bellpro.mini_project.domain.Board;
import com.bellpro.mini_project.dto.BoardRequestDto;
import com.bellpro.mini_project.repository.BoardRepository;
import com.bellpro.mini_project.security.UserDetailsImpl;
import com.bellpro.mini_project.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // 의존성 주입, final 필드에 대해 생성자를 생성,  @Autowired 대신 사용
@RestController          // JSON 데이터를 주고 받는 자동 응답기
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    // 게시글 작성
    @PostMapping("/board/write")
    public Board writeBoard(@RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){ // @RequestBody: JSON 변환
        String username = userDetails.getUsername();    // 사용자 인증 확인
        Board board = new Board(boardRequestDto, username); // 게시글 저장
        return boardRepository.save(board);
    }

    // 게시글 삭제
    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable Long id){ // @PathVariable: 요청 파라미터 값 전달 받음
        boardRepository.deleteById(id); // 게시글 삭제
        return id;
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

