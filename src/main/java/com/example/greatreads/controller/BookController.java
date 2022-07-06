package com.example.greatreads.controller;

import com.example.greatreads.repository.BookRepository;
import com.example.greatreads.Services.BookService;
import com.example.greatreads.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/book")
public class BookController {
    @Autowired BookRepository bookRepository;
    @Autowired  BookService bookService;

    @GetMapping(path  = "/all")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return new ResponseEntity(bookService.findAllBooks(), HttpStatus.OK);
    }

    @GetMapping(path  = "/")
    public ResponseEntity<List<BookDTO>> getAllBooksByType(@RequestParam (value = "type") String type){
        List<BookDTO> booksByType = bookService.findBooksByType(type);
        if (!booksByType.isEmpty()) {
            return new ResponseEntity(booksByType, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
