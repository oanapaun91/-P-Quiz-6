package com.example.greatreads.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "wishlist")
public class Wishlist {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
