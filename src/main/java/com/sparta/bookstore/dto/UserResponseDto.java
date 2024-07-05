package com.sparta.bookstore.dto;

import com.sparta.bookstore.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    // 아이디
    private Long userId;

    //이름
    private String name;

    //성별
    private String gender;

    //폰번호
    private String phoneNumber;

    //주소
    private String address;

    public UserResponseDto(User user){
        this.userId = user.getUserId();
        this.name = user.getName();
        this.gender = user.getGender();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
    }
}
