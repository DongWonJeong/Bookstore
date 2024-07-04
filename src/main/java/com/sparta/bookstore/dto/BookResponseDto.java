package com.sparta.bookstore.dto;

import com.sparta.bookstore.entity.Book;
import lombok.Getter;

import java.util.Date;

@Getter
public class BookResponseDto {

    // 아이디
    private Long id;

    //제목
    private String title;

    //저자
    private String writer;

    //언어
    private String publish;

    //등록일
    private Date nowDate;

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.writer = book.getWriter();
        this.publish = book.getPublish();
        this.nowDate = book.getNowDate();
    }
}
