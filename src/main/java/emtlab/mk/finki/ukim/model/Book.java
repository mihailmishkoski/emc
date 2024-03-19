package emtlab.mk.finki.ukim.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    private BookCategory bookCategory;

    @ManyToOne
    private Author author;

    private Integer availableCopies;

    private boolean available;

    public Book(String bookName, BookCategory bookCategory, Author author, Integer availableCopies) {
        this.bookName = bookName;
        this.bookCategory = bookCategory;
        this.author = author;
        this.availableCopies = availableCopies;
        available = availableCopies!=0;
    }
}
