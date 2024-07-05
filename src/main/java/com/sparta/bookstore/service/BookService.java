package com.sparta.bookstore.service;

import com.sparta.bookstore.dto.BookRequestDto;
import com.sparta.bookstore.dto.BookResponseDto;
import com.sparta.bookstore.entity.Book;
import com.sparta.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //도서 목록 조회
    public List<BookResponseDto> getBooks() {
        //DB 조회 -> 도서를 NowDate 오름차순으로!
        List<BookResponseDto> bookResponseDto = new ArrayList<>();
        List<Book> books = bookRepository.findAllByOrderByNowDateAsc();

        //조회된 각 도서에 대해 BookResponseDto로 변환하여 리스트에 추가.
        for (Book book : books) {
            bookResponseDto.add(new BookResponseDto(book));
        }

        return bookResponseDto;
    }

    //도서 정보 조회
    public BookResponseDto getBook(Long bookId) {
        //id가 없다면
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("아이디가 없습니다."));

        //id가 있다면
        return new BookResponseDto(book);
    }


    //도서 등록
    public BookResponseDto createBook(BookRequestDto requestDto) {

        //RequestDto -> Entity
        Book book = new Book(requestDto);

        // DB 저장
        Book saveBook = bookRepository.save(book);

        // Entity -> ResponseDto
        BookResponseDto bookResponseDto = new BookResponseDto(saveBook);

        return bookResponseDto;
    }
}
