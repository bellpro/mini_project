package com.bellpro.mini_project.service;

import com.bellpro.mini_project.domain.User;
import com.bellpro.mini_project.dto.UserInfoDto;
import com.bellpro.mini_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성,  @Autowired 대신 사용
@Service    // Service 명시, @Component 포항: Bean 등록
public class UserService {
    private final UserRepository userRepository;

    public String registerUser(UserInfoDto userInfoDto){
        String username = userInfoDto.getUsername();
        String email = userInfoDto.getEmail();

        // 아이디 중복 검사
        if (userRepository.existsByUsername(username)){
            return "중복된 아이디 입니다.";
        }
        // 이메일 중복 검사
        if (userRepository.existsByEmail(email)){
            return "중복된 이메일 입니다.";
        }

        // 클라이언트가 요청한 사용자 객체 생성 후 DB 저장
        User user = new User(userInfoDto);
        userRepository.save(user);

        // 정상 완료
        return "회원가입을 완료했습니다.";
    }
}
