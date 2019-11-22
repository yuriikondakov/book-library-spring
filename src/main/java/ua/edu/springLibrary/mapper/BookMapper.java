package ua.edu.springLibrary.mapper;


import org.springframework.stereotype.Component;
import ua.edu.springLibrary.domain.Book;
import ua.edu.springLibrary.entity.BookEntity;

import java.util.stream.Collectors;

@Component
public class BookMapper {
    private final AuthorMapper authorMapper;

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

   /* public BookEntity mapBookToBookEntity(Book book) {
        return BookEntity.builder()
                .withId(book.getId())
                .withName(book.getName())
                *//*.withAuthors(book.getAuthors().stream()
                        .map(authorMapper::mapAuthorToAuthorEntity)
                        .collect(Collectors.toList()))*//*
                .withDescription(book.getDescription())
                .withShelfId(book.getShelfId())
                *//* .withIssueDate(book.getIssueDate())
                 .withReturnDate(book.getReturnDate())*//*
                .build();*/
}