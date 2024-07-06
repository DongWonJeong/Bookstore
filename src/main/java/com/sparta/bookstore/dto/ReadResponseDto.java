package com.sparta.bookstore.dto;

import com.sparta.bookstore.entity.Book;
import com.sparta.bookstore.entity.User;
import lombok.Getter;

@Getter
public class ReadResponseDto {

    private String name;
    private String phoneNumber;
    private String title;
    private String writer;

    public ReadResponseDto(User user, Book book) {
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.title = book.getTitle();
        this.writer = book.getWriter();
    }
}
