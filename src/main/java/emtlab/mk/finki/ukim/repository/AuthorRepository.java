package emtlab.mk.finki.ukim.repository;

import emtlab.mk.finki.ukim.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<Author,Long> {
}
