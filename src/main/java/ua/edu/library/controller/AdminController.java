package ua.edu.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.library.domain.Author;
import ua.edu.library.domain.Book;
import ua.edu.library.service.AuthorService;
import ua.edu.library.service.BookService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/admin/addbook")
    public ModelAndView addingBook() {
        log.debug("adding book start");
        ModelAndView modelAndView = new ModelAndView();
        List<Author> authors = authorService.getAll();
        Book book = new Book();
        modelAndView.addObject("book", book);
        modelAndView.addObject("authorList", authors);
        modelAndView.setViewName("addbook");
        return modelAndView;
    }

    @PostMapping("/admin/addbook")
    public String saveNewBook(
            @RequestParam(value = "authorId") Integer authorId,
            Book book, BindingResult bindingResult, Model model) {
        log.debug("saving book start");
        book.setAuthors(authorService.getAuthorById(authorId));
        bookService.saveBook(book);
        model.addAttribute("addBookSuccessful" ,true);
        return "redirect:/admin/addbook";
    }
}
