package com.sparta.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "rental")
@NoArgsConstructor
public class Rental extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;

    private Long bookId;

    private Long userId;


    //대출일
    @Column(name= "nowDate", nullable = false)
    private LocalDate nowDate;

    //반납 상태
    @Column(name= "isReturned", nullable = false)
    private boolean isReturned;

    //도서 유무
    @Column(name= "isBookState", nullable = false)
    private boolean isBookState;

    //반납일
    @Column(name= "endDate", nullable = false)
    private LocalDate endDate;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setNowDate(LocalDate nowDate) {
        this.nowDate = nowDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setReturnedStatus(boolean returnStatus) {
        this.isReturned = returnStatus;
    }

    public void update() {
        this.isReturned = true;
        this.isBookState = true;
    }
}
