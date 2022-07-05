package com.example.greatreads.Repository;

import com.example.greatreads.Model.User;
import com.example.greatreads.dto.BookDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface WishlistRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO 'wishlist' " +
            "VALUES(NULL, ?1, ?2) ",
            nativeQuery = true)

    void insertWishlistBook (Integer reader_id, Integer book_id);
}
