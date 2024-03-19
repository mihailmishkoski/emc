package emtlab.mk.finki.ukim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emtlab.mk.finki.ukim.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
}
