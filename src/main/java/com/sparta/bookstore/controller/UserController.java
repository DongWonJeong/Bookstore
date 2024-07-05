package com.sparta.bookstore.controller;

import com.sparta.bookstore.dto.BookResponseDto;
import com.sparta.bookstore.dto.UserRequestDto;
import com.sparta.bookstore.dto.UserResponseDto;
import com.sparta.bookstore.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //user 조회
    @GetMapping("/users")
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    // user 등록
    @PostMapping("/users")
    public UserResponseDto createUser(@RequestBody UserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }

}
