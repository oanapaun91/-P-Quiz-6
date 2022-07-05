package com.example.greatreads.Services;

import com.example.greatreads.Model.ApprovedStatus;
import com.example.greatreads.Model.Book;
import com.example.greatreads.Repository.ReadBookRepository;
import com.example.greatreads.Repository.UserRepository;
import com.example.greatreads.dto.AddBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReadBookService {
@Autowired BookService bookService;
@Autowired ReadBookRepository readBookRepository;
@Autowired
UserRepository userRepository;

    public void addReadBook(int user_id, int book_id) {
        Book book = bookService.findById(book_id).orElseThrow();
        if ( book.getApprovedStatus().equals("approved")){
            readBookRepository.insertReadBook(user_id, book.getId());
        }

    }
}