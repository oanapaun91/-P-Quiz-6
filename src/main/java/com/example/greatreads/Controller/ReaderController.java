package com.example.greatreads.Controller;

import com.example.greatreads.Model.Book;
import com.example.greatreads.Model.User;
import com.example.greatreads.Repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ReaderController {
    private BookRepository bookRepository;
    private ReadBookRepository readBookRepository;
    private UserRepository userRepository;
    private WishlistRepository wishlistRepository;
    private ReviewRepository reviewRepository;

    public ReaderController(BookRepository bookRepository, ReadBookRepository readBookRepository,
                            UserRepository userRepository, WishlistRepository wishlistRepository,
                            ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.readBookRepository = readBookRepository;
        this.userRepository = userRepository;
        this.wishlistRepository = wishlistRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping(path  = "/book/all")
    public ResponseEntity<Optional<Book>> getAllBooks(){
       return new ResponseEntity(bookRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path  = "/book/type")
    public ResponseEntity<Optional<Book>> getAllBooks(@RequestParam (value = "type") String type){
        Optional<Book> booksByTitle = bookRepository.findByType(type);
        if (booksByTitle.isPresent()) {
            return new ResponseEntity(booksByTitle, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/read_book/add")
    public ResponseEntity<Book> addReadBook (@RequestHeader(value = "reader_id") Integer reader_id,
                                             @RequestParam(value="book_id") Integer book_id){
        Optional<User> user = userRepository.findById(reader_id);
        Optional<Book> book = bookRepository.findById(book_id);
        if (user.isPresent() && book.isPresent()){
            readBookRepository.insertReadBook(reader_id, book_id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/wishlist/add")
    //poate fi folosita pentru cand incepi sa citesti o caret si Is read = false; se adauga un nou endpoint pt cand temrini de citit artea care modifisa is Read to yes
    public ResponseEntity<Book> addWishlistBook (@RequestHeader(value = "reader_id") Integer reader_id,
                                             @RequestParam(value="book_id") Integer book_id){
        Optional<User> user = userRepository.findById(reader_id);
        Optional<Book> book = bookRepository.findById(book_id);
        if (user.isPresent() && book.isPresent()){
            wishlistRepository.insertWishlistBook(reader_id, book_id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/book/addReview")
    public ResponseEntity<Book> addReview (@RequestHeader(value = "reader_id") Integer reader_id,
                                                 @RequestParam(value="book_id") Integer book_id, @RequestBody String review){
        Optional<User> user = userRepository.findById(reader_id);
        Optional<Book> book = bookRepository.findById(book_id);
        if (user.isPresent() && book.isPresent()){
            if (book.get().isPending() == false) {
                reviewRepository.insertReview(reader_id, book_id, review);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
