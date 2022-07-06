package com.example.greatreads.repository;

import com.example.greatreads.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reviews " +
            "VALUES(NULL, ?1, ?2, ?3) ",
            nativeQuery = true)

    void insertReview (String review, Integer book_id, Integer reader_id);
}
