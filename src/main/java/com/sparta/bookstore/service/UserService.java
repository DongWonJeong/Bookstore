package com.sparta.bookstore.service;

import com.sparta.bookstore.dto.UserRequestDto;
import com.sparta.bookstore.dto.UserResponseDto;
import com.sparta.bookstore.entity.User;
import com.sparta.bookstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // user 목록 조회
    public List<UserResponseDto> getUsers() {

        List<UserResponseDto> userResponseDto = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for (User user : users) {
            userResponseDto.add(new UserResponseDto(user));
        }

        return userResponseDto;
    }

    // user 등록
    public UserResponseDto createUser(UserRequestDto requestDto) {

        //RequestDto -> Entity
        User user = new User(requestDto);

        // DB 저장
        User saveUser = userRepository.save(user);

        // Entity -> ResponseDto
        UserResponseDto userResponseDto = new UserResponseDto(saveUser);

        return userResponseDto;
    }
}
