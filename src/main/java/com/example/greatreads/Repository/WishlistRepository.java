package com.example.greatreads.Repository;

import com.example.greatreads.Model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO 'wishlist' " +
            "VALUES(NULL, ?1, ?2) ",
            nativeQuery = true)

    void insertWishlistBook (Integer reader_id, Integer book_id);
}
