package com.example.greatreads.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity(name = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "title")
    @NotBlank
    @NotNull
    private String title;

    @OneToOne
    @JoinColumn(name = "id_author")
    private User user;

    @Column(name = "type")
    @NotNull
    @NotBlank
    private String type;

    @NotBlank
    @NotNull
    @Column(name = "description")
    private String description;

    @Column(name = "approved")
    @Enumerated(EnumType.STRING)
    private ApprovedStatus approvedStatus;


    @ManyToMany(mappedBy="wishlistedBooks")
    @JsonIgnore
    private List<User> usersWithWishlist;

    @ManyToMany
    @JoinTable(name = "read_books",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<User> usersReadingThisBook = new ArrayList<User>();

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<ReadBook> booksRead;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<Review> review;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(user, book.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, user);
    }
}

