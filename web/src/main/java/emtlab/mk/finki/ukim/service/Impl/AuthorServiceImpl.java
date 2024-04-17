package emtlab.mk.finki.ukim.service.Impl;

import emtlab.mk.finki.ukim.exceptions.MyCustomException;
import emtlab.mk.finki.ukim.model.Author;
import emtlab.mk.finki.ukim.model.Book;
import emtlab.mk.finki.ukim.model.BookCategory;
import emtlab.mk.finki.ukim.model.Country;
import emtlab.mk.finki.ukim.model.dto.AuthorDto;
import emtlab.mk.finki.ukim.model.dto.BookDto;
import emtlab.mk.finki.ukim.repository.AuthorRepository;
import emtlab.mk.finki.ukim.repository.CountryRepository;
import emtlab.mk.finki.ukim.service.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> listAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Transactional
    @Override
    public void addAuthor(String name, String surname, Country country) {
        try{
            authorRepository.save(new Author(name,surname,country));
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot add a new author", e);
        }
    }

    @Override
    public void removeAuthor(Long authorId) {
        try{
            Author author = authorRepository.findById(authorId).orElse(null);
            if(author!=null)
                authorRepository.delete(author);
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot remove author", e);
        }
    }

    @Override
    public void editAuthor(Long authorId, String name, String surname, Country country) {
        try{
            Author author = authorRepository.findById(authorId).orElse(null);
            if(author!=null)
            {
                author.setAuthorName(name);
                author.setAuthorSurname(surname);
                author.setCountry(country);
                authorRepository.save(author);
            }
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot edit author", e);
        }
    }

    @Override
    public Optional<Author> addAuthor(AuthorDto authorDto) {
        try{
            String name = authorDto.getName();
            String surname = authorDto.getSurname();
            Country country = countryRepository.findById(authorDto.getCountry()).orElseThrow();

            Author author = new Author(name,surname,country);
            authorRepository.save(author);
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot add a new author", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Author> editAuthor(Long id, AuthorDto authorDto) {
        try{
            Author author = authorRepository.findById(id).orElseThrow();
            author.setAuthorName(authorDto.getName());
            author.setAuthorSurname(authorDto.getSurname());
            author.setCountry(countryRepository.findById(authorDto.getCountry()).orElseThrow());
            authorRepository.save(author);
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot add a new author", e);
        }
        return Optional.empty();
    }


}
