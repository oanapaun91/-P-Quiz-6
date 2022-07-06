package com.example.greatreads.repository;

import com.example.greatreads.model.ApprovedStatus;
import com.example.greatreads.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
  List<Book> findByType(String type);
  Optional<Book> findByTitle (String title);
  Optional<Book> findById (int id);

  @Transactional
  @Query(value = "SELECT * FROM books " +
        "JOIN users ON books.id_author = users.id " +
        "WHERE " +
        "users.email  = ?1 ",
        nativeQuery = true)
  List<Book> findByAuthor(String email);


  @Query(value = "SELECT * FROM books " +
          "JOIN users ON books.id_author = users.id " +
          "WHERE " +
          "books.title = ?1" +
          "AND " +
          "users.last_name  = ?2 ",
          nativeQuery = true)
  Optional<Book> findByTitleAndAuthor(String title, String author);

  List<Book> findByApprovedStatus(ApprovedStatus status);
}


