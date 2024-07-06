package com.sparta.bookstore.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {

    private Long userId;

    //이름
    private String name;

    //성별
    private String gender;

    //주민번호
    private String userNumber;

    //폰번호
    private String phoneNumber;

    //주소
    private String address;
}
