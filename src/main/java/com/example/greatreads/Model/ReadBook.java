package com.example.greatreads.Model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "read_books")
@Data
public class ReadBook {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


    @Column(name = "is_read")
    private boolean isRead;
}