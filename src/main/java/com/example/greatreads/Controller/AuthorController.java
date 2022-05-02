package com.example.greatreads.Controller;

import com.example.greatreads.Model.Book;
import com.example.greatreads.Model.Review;
import com.example.greatreads.Repository.BookCollection;
import com.example.greatreads.Repository.BookRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class AuthorController {
    BookRepository bookRepository;

    @GetMapping(path = "/book/my-books")
    public ResponseEntity<Optional<Book>> getPublishedBooks(@RequestHeader (value = "author_id") Integer author_id){
        Optional<Book> books = bookRepository.findById(author_id);
        if (books.isPresent()) {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/book/reviews")
    public ResponseEntity<Optional<Review>> seeReviews(@RequestParam(value ="book_id") Integer book_id){
        Optional<Book> books = bookRepository.findById(book_id);
        if (books.isPresent()){
            return new ResponseEntity(books.get().getReview(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    private BookCollection books = new BookCollection();
//
//    @RequestMapping(value = "/all-books", method = RequestMethod.GET)
//    public String viewAllBooks() {
//        JSONArray arr = new JSONArray(books.getAllBooks());
//        return arr.toString();
//    }
//
//    @RequestMapping(value = "/my-books", method = RequestMethod.GET)
//    public String viewMyBooks(@RequestParam(value = "author") String author) {
//        ArrayList<Book> filtered = new ArrayList<Book>();
//        for (Book item : books.getAllBooks()) {
//            if (item.getAuthor().getUserName().equals(author)) {
//                filtered.add(item);
//            }
//        }
//        JSONArray arr = new JSONArray(filtered);
//        return arr.toString();
//    }
//
//    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
//    public String addBook(@RequestBody Book book) {
//        books.addBook(book);
//        return new JSONObject(book).toString();
//    }
}
