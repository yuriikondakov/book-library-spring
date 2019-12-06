package ua.edu.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.edu.library.domain.Author;
import ua.edu.library.domain.Book;
import ua.edu.library.domain.BookTracking;
import ua.edu.library.service.AuthorService;
import ua.edu.library.service.BookService;
import ua.edu.library.service.BookTrackingService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookTrackingService bookTrackingService;

    @Value("${library.default.shelf.number}")
    private Integer shelfNumber;

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        log.error("!Request: " + req.getRequestURL() + " raised " + ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("errorMessage", "oops something went wrong");
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("index");
        return mav;
    }

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

    @GetMapping("/register")
    public ModelAndView showRegister(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "size", defaultValue = "10") Integer size) {
        ModelAndView modelAndView = new ModelAndView();
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<BookTracking> bookTrackings = bookTrackingService.getRegister(pageable);

        int totalPages = bookTrackings.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("bookTrackings", bookTrackings);
        modelAndView.addObject("today", LocalDate.now());
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @GetMapping("/bookbyshelf")
    public ModelAndView showBookByShelfInfo() {
        init();
        ModelAndView modelAndView = new ModelAndView();
        List<Book> books = bookService.getAll();
        modelAndView.addObject("books", books);
        modelAndView.setViewName("bookbyshelf");
        return modelAndView;
    }

    // @PostConstruct
    public void init() {
        List<Book> books = bookService.getAll();
        Map<Book, Integer> mapBookToShelfNumber = new HashMap<>();
        for (Book book : books) {
            /*Integer bookHash = book.getName().hashCode();
            Integer bookShelf = (bookHash % shelfNumber + shelfNumber) % shelfNumber + 1;
            book.setShelfId(bookShelf);*/
            bookService.updateBook(book);
        }
    }
}
