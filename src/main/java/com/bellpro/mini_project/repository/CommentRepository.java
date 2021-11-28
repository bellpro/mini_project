package com.bellpro.mini_project.repository;

import com.bellpro.mini_project.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속 받아서 사용 <엔티티, ID 자료형>
// 공통 인터페이스 사용 가능 (save, delete, findById ...)
public interface CommentRepository extends JpaRepository<Comment, Long> {   // Comment 테이블 연결
}
