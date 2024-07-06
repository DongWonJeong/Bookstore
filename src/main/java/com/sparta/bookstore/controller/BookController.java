package com.sparta.bookstore.controller;

import com.sparta.bookstore.dto.*;
import com.sparta.bookstore.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //도서 등록
    @PostMapping("/books")
    public BookResponseDto createBook(@RequestBody BookRequestDto requestDto) {
        return bookService.createBook(requestDto);
    }

    //도서 목록 조회
    @GetMapping("/books")
    public List<BookResponseDto> getBooks() {
        return bookService.getBooks();
    }

    //도서 정보 조회
    @GetMapping("/books/{bookId}")
    public BookResponseDto getBook(@PathVariable Long bookId) {
        return bookService.getBook(bookId);
    }

    // user 등록
    @PostMapping("/users")
    public UserResponseDto createUser(@RequestBody UserRequestDto requestDto) {
        return bookService.createUser(requestDto);
    }

    //도서 대출
    @PostMapping("/rental")
    public RentalResponseDto getRentalBook(@RequestBody RentalRequestDto requestDto) {
        return bookService.getRentalBook(requestDto);
    }

//    //도서 반납
//    @PutMapping("/rental/{rentalId}/return")
//    public Long getReturnBook(@PathVariable Long rentalId) {
//        return bookService.getReturnBook(rentalId);
//    }

//    //대출 내역 조회
//    @GetMapping("/rental/{userId}")
//    public List<RentalResponseDto> getRentalList(@PathVariable Long userId) {
//        return bookService.getRentalList(userId);
//    }

}
