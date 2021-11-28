package com.bellpro.mini_project.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 메소드 자동 생성
@NoArgsConstructor  // 기본 생성자 자동 생성
@Entity     // DB 테이블 설정
public class Comment extends Timestamped {
    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.AUTO)  // 자동으로 인덱스 증가
    private Long id;

    @Column(nullable = false)                   // 열 설정 (무조건 입력)
    private Long boardId;                       // 게시글 번호

    @Column(nullable = false)                   // 열 설정 (무조건 입력)
    private String writer;                      // 댓글 작성자

    @Column(nullable = false)                   // 열 설정 (무조건 입력)
    private String content;                     // 댓글 내용
}
