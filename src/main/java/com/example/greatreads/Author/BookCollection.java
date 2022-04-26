package com.example.greatreads.Author;

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
