package ua.edu.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.edu.library.domain.Book;
import ua.edu.library.entity.AuthorEntity;
import ua.edu.library.entity.BookEntity;
import ua.edu.library.mapper.BookMapper;
import ua.edu.library.repository.AuthorRepository;
import ua.edu.library.repository.BookRepository;
import ua.edu.library.service.BookService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

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

    @Override
    public void saveBook(Book book) {
        BookEntity bookEntity = bookMapper.mapBookToBookEntity(book);
        List<AuthorEntity> authorList = new ArrayList<>();
        authorList.add(authorRepository.findById(book.getAuthors().get(0).getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid author Id ")));
        bookEntity.setAuthors(authorList);
        bookRepository.save(bookEntity);
    }
}
