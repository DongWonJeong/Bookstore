package com.sparta.bookstore.repository;

import com.sparta.bookstore.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserId(Long userId);

    Optional<Rental> findByBookId(Long bookId);

//    // userId로 대출된 bookId 조회하는 쿼리
//    @Query(value = "SELECT bookId FROM rental WHERE userId = :userId ORDER BY nowDate ASC", nativeQuery = true)
//    List<Long> findBookIdsByUserId(Long userId);
}
