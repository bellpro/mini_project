package com.bellpro.mini_project.repository;

import com.bellpro.mini_project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속 받아서 사용 <엔티티, ID 자료형>
// 공통 인터페이스 사용 가능 (save, delete, findById ...)
public interface UserRepository extends JpaRepository<User, Long> { // User 테이블 연결
    boolean existsByUsername(String username);  // 아이디 중복 검사
    boolean existsByEmail(String email);        // 이메일 중복 검사
}
