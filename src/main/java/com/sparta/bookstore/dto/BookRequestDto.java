package com.sparta.bookstore.dto;

import lombok.Getter;

@Getter
public class BookRequestDto {

    private Long bookId;

    //제목
    private String title;

    //저자
    private String writer;

    //언어
    private String language;

    //출판사
    private String publish;
}
