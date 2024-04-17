package emtlab.mk.finki.ukim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emtlab.mk.finki.ukim.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByBookNameContainingIgnoreCase(String name);
}
