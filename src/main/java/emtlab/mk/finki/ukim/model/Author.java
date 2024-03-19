package emtlab.mk.finki.ukim.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data
@Table(name = "AuthorTable")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authorName;

    private String authorSurname;

    @ManyToOne
    private Country country;

    public Author(String authorName, String authorSurname, Country country) {
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.country = country;
    }
}
