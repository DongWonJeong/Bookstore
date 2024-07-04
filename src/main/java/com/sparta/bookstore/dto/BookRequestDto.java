package com.sparta.bookstore.dto;

import lombok.Getter;

@Getter
public class BookRequestDto {
    //제목
    private String title;

    //작성자명
    private String writer;

    //비밀번호
    private String language;

    //작성 내용
    private String publish;
}
