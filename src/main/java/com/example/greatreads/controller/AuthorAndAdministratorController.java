package com.example.greatreads.controller;

import com.example.greatreads.model.ApprovedStatus;
import com.example.greatreads.model.Book;
//import com.example.greatreads.Repository.BookCollection;
import com.example.greatreads.model.Review;
import com.example.greatreads.repository.BookRepository;
import com.example.greatreads.repository.ReviewRepository;
import com.example.greatreads.Services.BookService;
import com.example.greatreads.dto.AddBookDTO;
import com.example.greatreads.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/book")
public class AuthorAndAdministratorController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookService bookService;
    @Autowired
    ReviewRepository reviewRepository;


    @GetMapping(path = "/my-books")
    public ResponseEntity<List<Book>> getPublishedBooks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();
        return new ResponseEntity(bookService.findBooksByAuthor(currentPrincipalEmail), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public void addBook(@RequestBody AddBookDTO newBook) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();
        ResponseEntity<Book> book = bookService.addBook(newBook, currentPrincipalEmail);
        bookRepository.saveAndFlush(book.getBody());
    }

    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    @PutMapping(path = "/approval_request")
    public ResponseEntity<BookDTO> reviewApprovalRequest(@RequestParam (value = "bookId") Integer bookId, @RequestParam (value = "status") String approvedStatus) {
        return bookService.updateApprovedStatus(bookId, approvedStatus);
    }

    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    @GetMapping(path = "/status")
    public ResponseEntity<List<BookDTO>> getAllBooksByStatus (@RequestParam (value = "status") ApprovedStatus status){
        return new ResponseEntity(bookService.findByApprovedStatus(status), HttpStatus.OK);
    }


    @GetMapping(path = "/reviews")
    public ResponseEntity<Optional<Review>> seeReviews(@RequestParam(value ="book_id") Integer book_id) {
        Book book = bookRepository.findById(book_id).orElseThrow();
            return new ResponseEntity(book.getReview(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    @DeleteMapping(path = "/delete_review")
    public void deleteReview(@RequestParam (value = "review_id") Integer review_id){
        reviewRepository.deleteById(review_id);
    }
}



