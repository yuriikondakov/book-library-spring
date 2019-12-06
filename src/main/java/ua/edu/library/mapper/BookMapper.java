package ua.edu.library.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.edu.library.domain.Book;
import ua.edu.library.entity.BookEntity;

import java.util.stream.Collectors;

@Component
public class BookMapper {
    private final AuthorMapper authorMapper;

    @Value("${library.default.shelf.number}")
    private Integer shelfNumber;

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
        Integer bookHash = book.getName().hashCode();
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(book.getId());
        bookEntity.setName(book.getName());
        bookEntity.setDescription(book.getDescription());
        bookEntity.setShelfId((bookHash % shelfNumber + shelfNumber) % shelfNumber + 1);
        bookEntity.setAuthors(book.getAuthors().stream()
                .map(authorMapper::mapAuthorToAuthorEntity).collect(Collectors.toList()));
        return bookEntity;
    }
}