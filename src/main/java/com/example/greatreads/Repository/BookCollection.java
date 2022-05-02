package com.example.greatreads.Repository;

import com.example.greatreads.Model.Book;

import java.util.ArrayList;

public class BookCollection {

    private ArrayList<Book> listOfBooks = new ArrayList<Book>();

    public void addBook(Book book) {
        listOfBooks.add(book);
    }

    public ArrayList<Book> getAllBooks() {
        return listOfBooks;
    }
}
