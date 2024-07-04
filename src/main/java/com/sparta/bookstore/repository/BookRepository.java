package com.sparta.bookstore.repository;

import com.sparta.bookstore.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//CRUD 기능을 제공하는 repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByOrderByNowDateAsc();
}
