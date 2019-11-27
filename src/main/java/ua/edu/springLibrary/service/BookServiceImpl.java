package ua.edu.springLibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.edu.springLibrary.domain.Book;
import ua.edu.springLibrary.mapper.BookMapper;
import ua.edu.springLibrary.repository.BookRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
         bookRepository.findAll()
                .forEach(bookEntity -> books.add(bookMapper.mapBookEntityToBook(bookEntity)));
        return books;
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id)
                .map(bookMapper::mapBookEntityToBook)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
    }

    @Override
    public Page<Book> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Book> list = null;
        int size = (int) bookRepository.count();
        if (size < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, size);
            list = bookRepository.findAll(pageable).stream()
                    .map(bookMapper::mapBookEntityToBook).collect(Collectors.toList());
        }
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), size);
    }
}
