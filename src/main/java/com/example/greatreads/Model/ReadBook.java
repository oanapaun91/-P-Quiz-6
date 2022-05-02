package com.example.greatreads.Model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "read books")
@Data
public class ReadBook {
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

    @Column(name = "is read?")
    private boolean isRead;
}
