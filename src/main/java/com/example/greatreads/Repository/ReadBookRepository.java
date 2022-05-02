package com.example.greatreads.Repository;

import com.example.greatreads.Model.ReadBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ReadBookRepository extends JpaRepository<ReadBook, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO 'read books' " +
            "VALUES(NULL, ?1, ?2, 1) ",
            nativeQuery = true)

    void insertReadBook (Integer reader_id, Integer book_id);
}
