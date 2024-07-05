package com.sparta.bookstore.entity;

import com.sparta.bookstore.dto.BookRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "book")
@NoArgsConstructor
public class Book extends Timestamped{

    // 기본키 -> Id
    @Id
    // id값을 따로 할당하지 않아도 데이터베이스가 자동으로 AUTO_INCREMENT를 하여 기본키를 생성.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "writer", nullable = false)
    private String writer;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "publish", nullable = false)
    private String publish;

    public Book(BookRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.language = requestDto.getLanguage();
        this.publish = requestDto.getPublish();
    }

}
