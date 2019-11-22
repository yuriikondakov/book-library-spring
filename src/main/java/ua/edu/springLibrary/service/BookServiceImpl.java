package ua.edu.springLibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.edu.springLibrary.domain.Book;
import ua.edu.springLibrary.mapper.BookMapper;
import ua.edu.springLibrary.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookEntityRepository, BookMapper bookMapper) {
        this.bookRepository = bookEntityRepository;
        this.bookMapper = bookMapper;

    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
      //  bookRepository.findAll().forEach(bookEntity -> books.add(bookMapper.mapBookEntityToBook(bookEntity)));

        Pageable firstPageWithTenElements = PageRequest.of(0, 10);
        bookRepository.findAll(firstPageWithTenElements)
                .forEach(bookEntity -> books.add(bookMapper.mapBookEntityToBook(bookEntity)));
        return books;
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id)
                .map(bookMapper::mapBookEntityToBook)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
    }
}
