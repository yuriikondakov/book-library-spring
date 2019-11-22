package ua.edu.springLibrary.service;

import ua.edu.springLibrary.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Integer id);
}
