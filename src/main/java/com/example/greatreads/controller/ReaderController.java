package com.example.greatreads.controller;

import com.example.greatreads.model.Book;
import com.example.greatreads.model.User;
import com.example.greatreads.repository.*;
import com.example.greatreads.Services.BookService;
//import com.example.greatreads.Services.ReadBookService;
import com.example.greatreads.Services.ReadBookService;
import com.example.greatreads.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReaderController {
    private BookRepository bookRepository;
    private ReadBookRepository readBookRepository;
    private UserRepository userRepository;
    private UserService userService;
    @Autowired
    private ReadBookService readBookService;
    private BookService bookService;
    @Autowired
    private WishlistRepository wishlistRepository;
    private ReviewRepository reviewRepository;

    public ReaderController(BookRepository bookRepository, ReadBookRepository readBookRepository,
                            UserRepository userRepository,
                            ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.readBookRepository = readBookRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }


    @PostMapping(path = "/read_book/add")
    public ResponseEntity<?> addReadBook (@RequestParam(value="book_id") int book_id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalEmail).orElseThrow();
//
//        if (bookRepository.findById(book_id).isPresent()) {
            return readBookService.addReadBook(book_id, user.getId());
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return new ResponseEntity<>("No book with this id", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/read_book/mark_read")
    public ResponseEntity<?> markAsRead(@RequestParam(value="book_id") Integer book_id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalEmail).orElseThrow();

        if (((readBookRepository.findByBook(book_id).isPresent()) && (readBookRepository.findByBook(book_id).get().isRead() == false))) {
            readBookRepository.findByBook(book_id).get().setRead(true);
//            System.out.println("citita?" + readBookRepository.findByBook(book_id).get().isRead());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Cartea nu exista sau este deja citita", HttpStatus.BAD_REQUEST);

    }

    @GetMapping(path = "/read_book/")
    public @ResponseBody List<Book> getReadBooks(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalEmail).orElseThrow();
        return user.getReadBooks();
    }

    @PostMapping(path = "/addReview")
    public ResponseEntity<?> addReview (@RequestParam(value="book_id") Integer book_id, @RequestBody String review){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalEmail).orElseThrow();

        if (readBookRepository.findByBook(book_id).isPresent() && readBookRepository.findByBook(book_id).get().isRead() ) {
                reviewRepository.insertReview(review, book_id, user.getId());
                return new ResponseEntity<>("Review-ul a fost adaugat", HttpStatus.OK);
        }
        return new ResponseEntity<>("Cartea nu exista sau nu a fost citita", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/wishlist/add")
    public ResponseEntity<?> addWishlistBook (@RequestParam(value="book_id") Integer book_id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalEmail).orElseThrow();

        if (bookRepository.findById(book_id).isPresent()) {
            wishlistRepository.insertWishlistBook(user.getId(), book_id);
            return new ResponseEntity<>("Cartea a fost adaugata la wishlist", HttpStatus.OK);
        }
        return new ResponseEntity<>("Nu exista aceatsa carte", HttpStatus.BAD_REQUEST);
    }

//    @GetMapping(path = "/wishlist/")
//    public @ResponseBody List<Book> getWishlist(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalEmail = authentication.getName();
//        User user = userRepository.findByEmail(currentPrincipalEmail).orElseThrow();
//        return user.getWishlistedBooks();
//    }

}
