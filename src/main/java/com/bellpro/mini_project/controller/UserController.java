package com.bellpro.mini_project.controller;

import com.bellpro.mini_project.dto.UserInfoDto;
import com.bellpro.mini_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성, @Autowired 대신 사용
@Controller // 자동 응답기, controller 명시(@Component 포함): Bean 등록
public class UserController {
    private final UserService userService;

    // 회원가입 페이지 요청 (GET)
    @GetMapping("/user/signup")
    public String signup(){
        return "signup";    // 회원가입 페이지
    }

    // 회원가입 요청 (POST)
    @PostMapping("/user/signup")
    public String registerUser(UserInfoDto userInfoDto){
        return userService.registerUser(userInfoDto);       // 회원가입 성공 또는 오류 메세지
    }

}
