package ua.edu.library.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ua.edu.library.domain.Book;

import java.util.List;

public interface BookService {

    Book findById(Integer id);

    List<Book> getAll();

    Page<Book> getAll(Pageable pageable);

    void saveBook(Book book);

    Page<Book> getAllWithSearch(String searchField, Pageable pageable);

    void updateBook(Book book);
}
