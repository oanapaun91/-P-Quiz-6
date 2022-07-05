package com.example.greatreads.Services;

import com.example.greatreads.Model.ApprovedStatus;
import com.example.greatreads.Model.Book;
import com.example.greatreads.dto.AddBookDTO;
import com.example.greatreads.dto.BookDTO;
import com.example.greatreads.dto.WishlistBookDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    public BookDTO toBookDTO (Book book){
        String title = book.getTitle();
        String type = book.getType();
        String description = book.getDescription();
        String author = book.getUser().getFirstName() + " " + book.getUser().getLastName() ;
        ApprovedStatus approvedStatus = book.getApprovedStatus();
    return new BookDTO(title, type, description,author, approvedStatus);
    }

    public Book toBook (AddBookDTO newBook){
        Book book = new Book();
        book.setTitle(newBook.getTitle());
        book.setType(newBook.getType());
        book.setDescription(newBook.getDescription());
    return book;
    }

}
