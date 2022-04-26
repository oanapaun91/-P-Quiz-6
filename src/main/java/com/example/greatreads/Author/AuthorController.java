package com.example.greatreads.Author;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/author")
public class AuthorController {

    private BookCollection books = new BookCollection();

    @RequestMapping(value = "/all-books", method = RequestMethod.GET)
    public String viewAllBooks() {
        JSONArray arr = new JSONArray(books.getAllBooks());
        return arr.toString();
    }

    @RequestMapping(value = "/my-books", method = RequestMethod.GET)
    public String viewMyBooks(@RequestParam(value = "author") String author) {
        ArrayList<Book> filtered = new ArrayList<Book>();
        for (Book item : books.getAllBooks()) {
            if (item.getAuthor().getUserName().equals(author)) {
                filtered.add(item);
            }
        }
        JSONArray arr = new JSONArray(filtered);
        return arr.toString();
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String addBook(@RequestBody Book book) {
        books.addBook(book);
        return new JSONObject(book).toString();
    }
}
