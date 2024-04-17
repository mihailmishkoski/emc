package emtlab.mk.finki.ukim.service;

import emtlab.mk.finki.ukim.model.Author;
import emtlab.mk.finki.ukim.model.BookCategory;
import emtlab.mk.finki.ukim.model.Book;
import emtlab.mk.finki.ukim.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findById(Long id);

    List<Book> listAll();
    void addBook(String name, BookCategory bookCategory, Long authorId, Integer availableCopies);

    Optional<Book> addBook(BookDto bookDto);
    void removeBook(Long bookId);

    void editBook(Long bookId,String name, BookCategory bookCategory, Long authorId, Integer availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);
    void outOfStock(Long bookId);

    List<BookCategory> listCategories();

    List<Book> getBookByName(String name);

}
