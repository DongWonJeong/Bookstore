package com.sparta.bookstore.service;

import com.sparta.bookstore.dto.*;
import com.sparta.bookstore.entity.Book;
import com.sparta.bookstore.entity.Rental;
import com.sparta.bookstore.entity.User;
import com.sparta.bookstore.repository.BookRepository;
import com.sparta.bookstore.repository.RentalRepository;
import com.sparta.bookstore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository, RentalRepository rentalRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
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

    //도서 대출
    public RentalResponseDto getRentalBook(RentalRequestDto requestDto) {
        Long bookId = requestDto.getBookId();
        Long userId = requestDto.getUserId();

        //회원 여부
        if(!isUser(userId)){
            throw new IllegalArgumentException("해당 회원이 존재하지 않습니다.");
        }

        //반납하지 않은 도서 확인
        if(isBook(userId)){
            throw new IllegalArgumentException("반납하지 않은 도서가 있어 대출이 불가능 합니다.");
        }

        //도서 대출 상태 여부
        if(takeBook(bookId)){
            throw new IllegalArgumentException("해당 도서는 이미 대출 중입니다!");
        }

        //대출 내역 기록
        Rental rental = new Rental();
        rental.setUserId(userId);
        rental.setBookId(bookId);
        //기본 상태는 대출중!
        rental.setReturnedStatus(false);

        // 대출일 설정
        rental.setNowDate(LocalDate.now());
        // 현재 시간을 대출일로 설정
        rental.setEndDate(LocalDate.now().plusDays(7));

        // 대출 내역 저장
        rentalRepository.save(rental);

        return new RentalResponseDto(rental);
    }

    // 도서 반납
    @Transactional
    public Long getReturnBook(Long rentalId) {
        // rentalId로 대출 정보 조회
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("선택한 도서는 존재하지 않습니다."));

        rental.update();

        return rentalId;
    }

    // 내역 조회
//    @Transactional
//    public List<ReadResponseDto> getRentalList(Long userId) {
//
//        List<ReadResponseDto> list = new ArrayList<>();
//
//        List<Long> longList = findBookIdsByUserId(userId);
//
//        User user = userRepository.findById(userId).orElseThrow(() ->
//                new IllegalArgumentException("선택한 회원은 존재하지 않습니다.")
//        );
//
//         for (Long bookId : longList) {
//              Book book = bookRepository.findById(bookId).orElseThrow(() ->
//                new IllegalArgumentException("선택한 책은 존재하지 않습니다.")
//        );
//        ReadResponseDto responseDto = new ReadResponseDto(user, book);
//        list.add(responseDto);
//        }
//
//        return list;
//    }


    //회원 여부
    private boolean isUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("해당 아이디가 존재하지 않습니다");
        }
        return userRepository.existsById(userId);
    }

    //반납하지 않은 도서 확인
    public boolean isBook(Long userId) {
        //회원이 대출한 도서 목록 조회
        List<Rental> rentalUser = rentalRepository.findByUserId(userId);
        //대출된 도서 중 반납되지 않은 도서 여부
        for (Rental rental : rentalUser) {
            if(!rental.isReturned()){
                //반납 하지 않은 도서 O
                return true;
            }
        }
        // 반납하지 않은 도서 x
        return false;
    }

    //도서 대출 상태 여부
    public boolean takeBook(Long bookId) {
        Optional<Rental> rental = rentalRepository.findByBookId(bookId);

        // 대출 기록이 존재하면 해당 도서는 이미 대출 중
        return rental.isPresent();
    }

//    // 회원이 대출한 책의 Id들을 조회하는 메서드
//    public List<Long> findBookIdsByUserId(Long userId) {
//        return rentalRepository.findBookIdsByUserId(userId);
//    }

}
