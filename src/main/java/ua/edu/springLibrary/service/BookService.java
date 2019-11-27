package ua.edu.springLibrary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.edu.springLibrary.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Integer id);

    Page<Book> findPaginated(Pageable pageable);
}
