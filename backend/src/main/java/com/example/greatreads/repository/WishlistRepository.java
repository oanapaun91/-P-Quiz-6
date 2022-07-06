package com.example.greatreads.repository;

import com.example.greatreads.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface WishlistRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO wishlist " +
            "VALUES(?1, ?2) ",
            nativeQuery = true)

    void insertWishlistBook (Integer reader_id, Integer book_id);
}
