package ua.edu.springLibrary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.springLibrary.repository.BookRepository;
import ua.edu.springLibrary.service.BookService;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookRepository bookEntityRepository, BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "catalog";
    }

    @GetMapping("/{id}")
    public String getBookById (@PathVariable("id") Integer id, Model model){
        model.addAttribute("book",  bookService.findById(id));
        return "book";
    }
}
