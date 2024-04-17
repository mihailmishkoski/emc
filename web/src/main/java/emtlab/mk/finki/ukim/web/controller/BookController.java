package emtlab.mk.finki.ukim.web.controller;

import emtlab.mk.finki.ukim.model.Author;
import emtlab.mk.finki.ukim.model.Book;
import emtlab.mk.finki.ukim.model.BookCategory;
import emtlab.mk.finki.ukim.service.AuthorService;
import emtlab.mk.finki.ukim.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import emtlab.mk.finki.ukim.service.BookService;

@Controller
@SessionAttributes("currentUserId")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    private final CountryService countryService;

    public BookController(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping("/books")
    public String getBookPage(Model model)
    {
        model.addAttribute("books", bookService.listAll());
        return "booksView";
    }
    @GetMapping("/addBook")
    public String getAddBookPage(Model model)
    {
        model.addAttribute("authors", authorService.listAll());
        model.addAttribute("countries", countryService.listAll());
        model.addAttribute("categories", BookCategory.values());
        return "addBookView";
    }
    @PostMapping("/addBook")
    public String addBook(@RequestParam String bookName,
                          @RequestParam BookCategory bookCategory,
                          @RequestParam Long authorId,
                          @RequestParam Integer availableCopies)
    {
        bookService.addBook(bookName,bookCategory,authorId,availableCopies);
        return "redirect:/books";
    }

    @PostMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id)
    {
        bookService.removeBook(id);
        return "redirect:/books";
    }
    @PostMapping("/outOfStock/{id}")
    public String markOutOfStocks(@PathVariable Long id)
    {
        bookService.outOfStock(id);
        return "redirect:/books";
    }
    @GetMapping("/editBook/{id}")
    public String getEditBookPage(@PathVariable Long id, Model model)
    {
        Book book = bookService.findById(id).orElse(null);
        model.addAttribute("book",book);
        model.addAttribute("authors", authorService.listAll());
        model.addAttribute("categories", BookCategory.values());
        return "addBookView";
    }
    @PostMapping("/addBook/{id}")
    public String editBook(@PathVariable Long id,
                           @RequestParam String bookName,
                           @RequestParam BookCategory bookCategory,
                           @RequestParam Long authorId,
                           @RequestParam Integer availableCopies)
    {
        bookService.editBook(id,bookName,bookCategory,authorId,availableCopies);
        return "redirect:/books";
    }
}
