package ua.edu.library.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.edu.library.domain.Book;

public interface BookService {

    Book findById(Integer id);

    Page<Book> findPaginated(Pageable pageable);

    void saveBook(Book book);
}
