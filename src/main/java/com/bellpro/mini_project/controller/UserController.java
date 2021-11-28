package com.bellpro.mini_project.controller;

import com.bellpro.mini_project.dto.UserInfoDto;
import com.bellpro.mini_project.service.UserService;
import com.bellpro.mini_project.validator.UserInfoDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성, @Autowired 대신 사용
@Controller // 자동 응답기, controller 명시(@Component 포함): Bean 등록
public class UserController {
    private final UserService userService;
    private final UserInfoDtoValidator userInfoDtoValidator;

    // 아이디 중복검사, 이메일 중복검사, 비밀번호 확인
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(userInfoDtoValidator); // 검증 결과를 errors 에 넣음
    }

    // 회원가입 페이지 요청 (GET)
    @GetMapping("/user/signup")
    public String signup(Model model){
        model.addAttribute("UserInfoDto", new UserInfoDto()); // 빈 dto 객체 생성하여 변수 전달
        return "user/signup";    // 회원가입 페이지 (초기)
    }

    // 회원가입 요청 (POST)
    @PostMapping("/user/signup")
    public String registerUser(@Valid UserInfoDto userInfoDto, Errors errors, Model model){
        // 오류 처리
        if (errors.hasErrors()){
            model.addAttribute("UserInfoDto", userInfoDto); // dto 객체 생성하여 변수 전달
            return "user/signup";   // 회원가입 페이지 이동 (오류 내용 포함)
        }

        // 정상 처리
        userService.registerUser(userInfoDto);  // service 로 dto 전달
        return "redirect:/user/login";   // 로그인 페이지 (templates/user/login.html) 이동
    }

    // 사용자 로그인 페이지 요청 (GET)
    @GetMapping("/user/login")
    public String login(){
        return "user/login";    // 로그인 페이지 (초기)
    }

}
