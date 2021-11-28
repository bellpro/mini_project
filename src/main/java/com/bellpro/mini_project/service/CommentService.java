package com.bellpro.mini_project.service;

import com.bellpro.mini_project.domain.Comment;
import com.bellpro.mini_project.dto.CommentRequestDto;
import com.bellpro.mini_project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor    // 의존성 주입, final 필드에 대해 생성자를 생성,  @Autowired 대신 사용
@Service    // Service 명시(@Component 포함): Bean 등록
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional  // 트랜잭션 처리
    public void updateComment(Long id, CommentRequestDto commentRequestDto){
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        comment.update(commentRequestDto);
    }
}
