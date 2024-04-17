package emtlab.mk.finki.ukim.service.Impl;

import emtlab.mk.finki.ukim.model.Author;
import emtlab.mk.finki.ukim.model.BookCategory;
import emtlab.mk.finki.ukim.model.dto.BookDto;
import emtlab.mk.finki.ukim.model.events.ProductCreatedEvent;
import emtlab.mk.finki.ukim.repository.AuthorRepository;
import emtlab.mk.finki.ukim.repository.BookRepository;
import emtlab.mk.finki.ukim.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import emtlab.mk.finki.ukim.exceptions.MyCustomException;
import emtlab.mk.finki.ukim.model.Book;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private final ApplicationEventPublisher applicationEventPublisher;
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }
    @Transactional
    @Override
    public void addBook(String name, BookCategory bookCategory, Long authorId, Integer availableCopies) {
        try{
            Author author = authorRepository.findById(authorId).orElse(null);
            Book book = new Book(name,bookCategory,author,availableCopies);
            bookRepository.save(book);
            this.applicationEventPublisher.publishEvent(new ProductCreatedEvent(book,book.getAvailableCopies()));
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot add a new book", e);
        }
    }

    @Transactional
    @Override
    public Optional<Book> addBook(BookDto bookDto) {
        try{
            String name = bookDto.getName();
            Author author = authorRepository.findById(bookDto.getAuthor()).orElse(null);
            BookCategory bookCategory = bookDto.getCategory();
            int availableCopies = bookDto.getAvailableCopies();
            Book book = new Book(name,bookCategory,author,availableCopies);
            bookRepository.save(book);
            this.applicationEventPublisher.publishEvent(new ProductCreatedEvent(book,book.getAvailableCopies()));
            return Optional.of(book);
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot add a new book", e);
        }
    }
    @Override
    public void removeBook(Long bookId) {
        try{
            Book book = bookRepository.findById(bookId).orElse(null);
            if(book!=null)
                bookRepository.delete(book);
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot remove a book", e);
        }
    }

    @Override
    public void editBook(Long bookId, String name, BookCategory bookCategory, Long authorId, Integer availableCopies) {
        try{
            Book book = bookRepository.findById(bookId).orElse(null);
            Author author = authorRepository.findById(authorId).orElse(null);
            if(book!=null)
            {
                book.setBookName(name);
                book.setBookCategory(bookCategory);
                book.setAuthor(author);
                book.setAvailableCopies(availableCopies);
                bookRepository.save(book);
            }
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot edit a book", e);
        }
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthor()).orElseThrow();
        Book book = bookRepository.findById(id).orElseThrow();

        book.setBookName(bookDto.getName());
        book.setBookCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setAvailable(bookDto.isAvailable());
        bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public void outOfStock(Long bookId) {
        try{
            Book book = bookRepository.findById(bookId).orElse(null);
            if(book!=null)
                book.setAvailable(!book.isAvailable());
                book.setAvailableCopies(0);
                bookRepository.save(book);
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot remove a book", e);
        }
    }

    @Override
    public List<BookCategory> listCategories() {
        return Arrays.stream(BookCategory.values()).toList();
    }

    @Override
    public List<Book> getBookByName(String name) {
        return bookRepository.findAllByBookNameContaining(name);
    }
}
