package ua.edu.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/admin")
public class AdminController {
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/addbook")
    public ModelAndView addingBook() {
        ModelAndView modelAndView = new ModelAndView();
        List<Author> authors = authorService.getAll();
        Book book = new Book();
        modelAndView.addObject("book", book);
        modelAndView.addObject("authorList", authors);
        modelAndView.setViewName("addbook");
        return modelAndView;
    }

    @PostMapping("/addbook")
    public String saveNewBook(@RequestParam(value = "authorId") Integer authorId, Book book, Model model) {
        log.debug("saving book start");
        book.setAuthors(authorService.getAuthorById(authorId));
        bookService.saveBook(book);
        model.addAttribute("addBookSuccessful", "Book " + book.getName() + " added successfully");
        return "redirect:/admin/addbook";
    }

    @GetMapping("/addauthor")
    public ModelAndView addingAuthor() {
        ModelAndView modelAndView = new ModelAndView();
        Author author = new Author();
        modelAndView.addObject("author", author);
        modelAndView.setViewName("addauthor");
        return modelAndView;
    }

    @PostMapping("/addauthor")
    public String saveNewAuthor(Author author, Model model) {
        if (authorService.save(author)) {
            model.addAttribute("addAuthorMessage", "Author "
                    + author.getFirstName() + " " + author.getLastName() +
                    " added successfully");
            return "redirect:/admin/addbook";
        } else {
            model.addAttribute("addAuthorMessage", "Author "
                    + author.getFirstName() + " " + author.getLastName() + " is already exist");
            return "redirect:/admin/addauthor";
        }
    }
}
