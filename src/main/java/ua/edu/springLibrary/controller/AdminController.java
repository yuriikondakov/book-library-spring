package ua.edu.springLibrary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.springLibrary.domain.Author;
import ua.edu.springLibrary.service.AuthorService;
import ua.edu.springLibrary.service.BookService;

import java.util.List;

@Slf4j
@Controller("AdminController")
@RequiredArgsConstructor
public class AdminController {
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/admin/addbook")
    public ModelAndView addBook(Model model) {
        log.debug("adding book start");
        ModelAndView modelAndView = new ModelAndView();
        List<Author> authors = authorService.getAll();
        log.debug(authors.toString());
        modelAndView.addObject("authorList", authors);
        modelAndView.setViewName("addbook");
        return modelAndView;
    }
}
