package com.example.greatreads.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity(name = "books")
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
    private List<User> usersWithWishlist;

    @ManyToMany
    @JoinTable(name = "read_books",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<User> usersReadingThisBook = new ArrayList<User>();

    @OneToMany(mappedBy = "book")
    @JsonBackReference
    private Set<ReadBook> booksRead;

    @OneToMany(mappedBy = "book")
    @JsonBackReference
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


//@Data
//@Entity(name = "books")
//public class Book {

//
//    @ManyToMany(mappedBy = "wishListBooks")
//    @JsonIgnore
//    private Set<User> usersHavingBookOnWishlist;
//
//    @OneToMany(mappedBy = "book")
//    @JsonBackReference
//    private Set<Wishlist> booksOnWishlist;
//
//    @ManyToMany(mappedBy = "readBooks")
//    @JsonIgnore
//    private Set<User> usersReadBook;
//

//

//

//}
