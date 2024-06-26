package emtlab.mk.finki.ukim.web.controller.rest;

import emtlab.mk.finki.ukim.model.Book;

import emtlab.mk.finki.ukim.model.BookCategory;
import emtlab.mk.finki.ukim.model.dto.BookDto;


import emtlab.mk.finki.ukim.service.BookService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public List<Book> findAll(){
        return bookService.listAll();
    }

    @GetMapping("/categories")
    public List<BookCategory> findCategories() {
        return bookService.listCategories();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
         bookService.removeBook(id);
    }
    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return this.bookService.addBook(bookDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.edit(id,bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PostMapping("/outOfStock/{id}")
    public String markOutOfStocks(@PathVariable Long id)
    {
        bookService.outOfStock(id);
        return "redirect:/books";
    }
    @GetMapping("/getByName/{name}")
    public List<Book> getBookById(@PathVariable String name)
    {
        return bookService.getBookByName(name);
    }
}
