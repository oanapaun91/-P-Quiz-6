package com.example.greatreads.Controller;

import com.example.greatreads.Model.Book;
import com.example.greatreads.Repository.BookRepository;
import com.example.greatreads.Services.BookService;
import com.example.greatreads.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<BookDTO>> getAllBooks(@RequestParam (value = "type") String type){
        List<BookDTO> booksByType = bookService.findBooksByType(type);

        if (booksByType != null) {
            return new ResponseEntity(booksByType, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
