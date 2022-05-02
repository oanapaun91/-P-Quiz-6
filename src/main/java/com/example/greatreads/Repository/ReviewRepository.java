package com.example.greatreads.Repository;

import com.example.greatreads.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO 'review' " +
            "VALUES(NULL, ?1, ?2, ?3) ",
            nativeQuery = true)

    void insertReview (Integer reader_id, Integer book_id, String review);
}
