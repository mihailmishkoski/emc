package emtlab.mk.finki.ukim.web.controller.rest;

import emtlab.mk.finki.ukim.model.Author;
import emtlab.mk.finki.ukim.model.Book;
import emtlab.mk.finki.ukim.model.dto.AuthorDto;
import emtlab.mk.finki.ukim.model.dto.BookDto;
import emtlab.mk.finki.ukim.repository.AuthorRepository;
import emtlab.mk.finki.ukim.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {

    private final AuthorService authorService;


    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping
    public List<Author> findAll(){
        return authorService.listAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        return this.authorService.findAuthorById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        authorService.removeAuthor(id);
    }
    @PostMapping("/add")
    public ResponseEntity<Author> save(@RequestBody AuthorDto authorDto) {
        return this.authorService.addAuthor(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> edit(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        return this.authorService.editAuthor(id,authorDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
