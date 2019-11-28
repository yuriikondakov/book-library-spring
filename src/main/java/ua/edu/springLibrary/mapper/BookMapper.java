package ua.edu.springLibrary.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.edu.springLibrary.domain.Book;
import ua.edu.springLibrary.entity.BookEntity;

import java.util.stream.Collectors;

@Component
public class BookMapper {
    private final AuthorMapper authorMapper;

    @Autowired
    public BookMapper(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public Book mapBookEntityToBook(BookEntity bookEntity) {
        return new Book(bookEntity.getId(),
                bookEntity.getAuthors().stream()
                        .map(authorMapper::mapAuthorEntityToAuthor)
                        .collect(Collectors.toList()),
                bookEntity.getName(),
                bookEntity.getDescription(),
                bookEntity.getShelfId());
    }

    public BookEntity mapBookToBookEntity(Book book) {
        BookEntity bookEntity = new BookEntity();
       // bookEntity.setId(book.getId());
        bookEntity.setName(book.getName());
        bookEntity.setDescription(book.getDescription());
        bookEntity.setShelfId(1);
        bookEntity.setAuthors(book.getAuthors().stream()
                .map(authorMapper::mapAuthorToAuthorEntity).collect(Collectors.toList()));
        return bookEntity;
    }
}