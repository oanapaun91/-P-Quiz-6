package com.example.greatreads.repository;

import com.example.greatreads.model.Book;
import com.example.greatreads.model.ReadBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ReadBookRepository extends JpaRepository<ReadBook, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO read_books " +
            "VALUES(NULL, 0, ?1, ?2) ",
            nativeQuery = true)
    void insertReadBook (Integer book_id, Integer reader_id);



    @Transactional
    @Query(value = "SELECT * FROM read_books " +
            "WHERE " +
            "read_books.book_id  = ?1 ",
            nativeQuery = true)
    Optional<ReadBook> findByBook(Integer Id);

}
