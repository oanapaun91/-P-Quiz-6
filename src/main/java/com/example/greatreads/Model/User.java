package com.example.greatreads.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public User(Integer id, String email, String password, String firstName, String lastName, UserType userType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
//    @Email
    private String email;

    @Column(name = "password")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "password must have digit + lowercase + uppercase + punctuation + symbol")
    private String password;

    @Column(name = "first_name")
//    @NotNull(message = "firstName must be not-null")
//    @NotBlank(message = "firstName must be a non-empty string")
    private String firstName;

    @Column(name = "last_name")
//    @NotNull(message = "lastName must be not-null")
//    @NotBlank(message = "lastName must be a non-empty string")
    private String lastName;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @ManyToMany
    @JoinTable(name = "wishlist",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "book_id") })
    private List<Book> wishlistedBooks = new ArrayList<Book>();

    @ManyToMany(mappedBy="usersReadingThisBook")
    private List<Book> readBooks;



    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<ReadBook> read;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private Set<Review> review;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, userType);
    }
}

//
//@Data
//@Entity(name = "users")
//public class User {

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "wishlist",
//            joinColumns = @JoinColumn(name = "Reader_id"),
//            inverseJoinColumns = @JoinColumn(name = "Book_id"))
//    private Set<Book> wishListBooks;
//
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private Set<Wishlist> wishlist;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "read books",
//            joinColumns = @JoinColumn(name = "Reader_id"),
//            inverseJoinColumns = @JoinColumn(name = "Book_id"))
//    private Set<Book> readBooks;
//
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private Set<ReadBook> read;
//
//    @OneToOne(mappedBy = "user")
//    @JsonBackReference
//    private Book book;
//
//    @OneToMany(mappedBy = "user")
//    @JsonBackReference
//    private Set<Review> review;
//

//}
