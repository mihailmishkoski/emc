package emtlab.mk.finki.ukim.service;

import emtlab.mk.finki.ukim.model.Author;
import emtlab.mk.finki.ukim.model.Country;
import emtlab.mk.finki.ukim.model.dto.AuthorDto;
import emtlab.mk.finki.ukim.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> listAll();

    Optional<Author> findAuthorById(Long id);
    void addAuthor(String name, String surname, Country country);
    void removeAuthor(Long authorId);

    void editAuthor(Long authorId, String name, String surname, Country country);

    Optional<Author> addAuthor(AuthorDto authorDto);

    Optional<Author> editAuthor(Long id, AuthorDto authorDto);
}
