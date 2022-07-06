package com.example.greatreads.Services;

import com.example.greatreads.model.Book;
import com.example.greatreads.repository.ReadBookRepository;
import com.example.greatreads.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReadBookService {
@Autowired
BookService bookService;
@Autowired
ReadBookRepository readBookRepository;
@Autowired
UserRepository userRepository;

    public ResponseEntity<?> addReadBook(int book_id, int user_id) {
        Book book = bookService.findById(book_id).orElseThrow();

        if (book.getApprovedStatus().getStatus().equals("APPROVED")){
            readBookRepository.insertReadBook(book.getId(), user_id);
            return new ResponseEntity<>("Book was added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book is not published", HttpStatus.BAD_REQUEST);
        }

    }
}