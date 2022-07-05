package com.example.greatreads.Controller;

import com.example.greatreads.Model.Book;
import com.example.greatreads.Model.User;
import com.example.greatreads.Repository.*;
import com.example.greatreads.Services.BookService;
//import com.example.greatreads.Services.ReadBookService;
import com.example.greatreads.Services.ReadBookService;
import com.example.greatreads.Services.UserService;
import com.example.greatreads.dto.WishlistBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

        if (bookRepository.findById(book_id).isPresent()) {
            readBookService.addReadBook(user.getId(), book_id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/wishlist/add")
    public ResponseEntity<?> addWishlistBook (@RequestParam(value="book_id") Integer book_id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalEmail).orElseThrow();

        if (bookRepository.findById(book_id).isPresent()) {
            wishlistRepository.insertWishlistBook(user.getId(), book_id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping(path = "/wishlist/")
    public @ResponseBody List<Book> getWishlist(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalEmail).orElseThrow();
        return user.getWishlistedBooks();
    }

    @GetMapping(path = "/read_book/")
    public @ResponseBody List<Book> getReadBooks(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalEmail).orElseThrow();
        return user.getReadBooks();
    }

    @PutMapping(path = "/read_book/mark_read")
    public ResponseEntity<?> markAsRead(@RequestParam(value="book_id") Integer book_id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalEmail).orElseThrow();
        if ((readBookRepository.findById(book_id).isPresent()) && (!readBookRepository.findById(book_id).get().isRead())) {
            readBookRepository.findById(book_id).get().setRead(true);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PostMapping(path = "/addReview")
    public ResponseEntity<?> addReview (@RequestParam(value="book_id") Integer book_id, @RequestBody String review){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalEmail).orElseThrow();

        if (readBookRepository.findById(book_id).isPresent() && readBookRepository.findById(book_id).get().isRead() ) {
                reviewRepository.insertReview(user.getId(), book_id, review);
                return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
