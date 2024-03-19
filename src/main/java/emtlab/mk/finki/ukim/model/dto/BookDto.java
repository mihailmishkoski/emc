package emtlab.mk.finki.ukim.model.dto;

import emtlab.mk.finki.ukim.model.BookCategory;
import lombok.Data;

@Data
public class BookDto {
    private String name;
    private BookCategory category;
    private Long author;
    private Integer availableCopies;

    private boolean isAvailable;

    public BookDto(String name, BookCategory category, Long author, Integer availableCopies, boolean isAvailable) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.isAvailable = isAvailable;
    }
}
