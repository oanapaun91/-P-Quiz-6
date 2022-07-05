package com.example.greatreads.Services;

import com.example.greatreads.Model.ApprovedStatus;
import com.example.greatreads.Model.Book;
import com.example.greatreads.Model.User;
import com.example.greatreads.Repository.BookRepository;
import com.example.greatreads.Repository.UserRepository;
import com.example.greatreads.dto.AddBookDTO;
import com.example.greatreads.dto.BookDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    Mapper mapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    public Optional<Book> findByTitleAndAuthor(String lastName, String title) {
        return bookRepository.findByTitleAndAuthor(lastName, title);
    }

    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    public List<BookDTO> findAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(mapper::toBookDTO)
                .collect(toList());
    }

    public List<BookDTO> findBooksByType(String type) {
        return bookRepository.findByType(type)
                .stream()
                .map(mapper::toBookDTO)
                .collect(toList());
    }

    public ResponseEntity<List<BookDTO>> findBooksByAuthor(String email) {
        if (userService.isAuthor(email)) {
            return new ResponseEntity<>((bookRepository.findByAuthor(email)
                    .stream()
                    .map(mapper::toBookDTO)
                    .collect(toList())), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Book> addBook(AddBookDTO newBook, String email) {
        Book book = new Book();
        Optional<Book> existentBook = bookRepository.findByTitle(newBook.getTitle());
        if (userService.isAuthor(email)) {
            if (!existentBook.isPresent()) {
                book = mapper.toBook(newBook);
                book.setUser(userRepository.findByEmail(email).orElseThrow());
                book.setApprovedStatus(ApprovedStatus.valueOf("PENDING"));
                return new ResponseEntity<>(book, HttpStatus.OK);

            } else if (!existentBook.get().getUser().getEmail().equals(email)) {
                book = mapper.toBook(newBook);
                book.setUser(userRepository.findByEmail(email).orElseThrow());
                book.setApprovedStatus(ApprovedStatus.valueOf("PENDING"));
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        } else if (userService.isAdministrator(email)) {
            if (!existentBook.isPresent()) {
                book = mapper.toBook(newBook);
                book.setUser(userRepository.findByEmail(email).orElseThrow());
                book.setApprovedStatus(ApprovedStatus.valueOf("APPROVED"));

                return new ResponseEntity<>(book, HttpStatus.OK);
            } else if (!existentBook.get().getUser().getEmail().equals(email)) {
                book = mapper.toBook(newBook);
                book.setUser(userRepository.findByEmail(email).orElseThrow());
                book.setApprovedStatus(ApprovedStatus.valueOf("APPROVED"));
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<BookDTO> updateApprovedStatus(Book book, String newStatus){
        if (book.getApprovedStatus().equals("PENDING")){
            switch (newStatus)
            {
                case "APPROVED":
                    book.setApprovedStatus(ApprovedStatus.valueOf("APPROVED"));
                    break;

                case "REJECTED":
                    book.setApprovedStatus(ApprovedStatus.valueOf("REJECTED"));
                    break;
            }
            bookRepository.saveAndFlush(book);
            return new ResponseEntity<>(mapper.toBookDTO(book), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    public ResponseEntity<BookDTO> updateApprovedStatusToRejected(Book book){
//        if (book.getApprovedStatus().equals("PENDING")){
//            book.setApprovedStatus(ApprovedStatus.valueOf("REJECTED"));
//            bookRepository.saveAndFlush(book);
//            return new ResponseEntity<>(mapper.toBookDTO(book), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//    public ResponseEntity<Book> addBookByAuthor (AddBookDTO newBook, String email) {
//        if (userService.isAuthor(email)) {
//            return addBook(newBook, email);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//    public ResponseEntity<Book> addBookByAdministrator (AddBookDTO newBook, String email) {
//        if (userService.isAdministrator(email)) {
//            return addBook(newBook, email);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }

}
