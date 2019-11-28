package ua.edu.springLibrary.service;

import ua.edu.springLibrary.domain.Author;
import ua.edu.springLibrary.domain.Book;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    List<Author> getAuthorById(Integer id);

}
