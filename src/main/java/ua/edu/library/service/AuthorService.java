package ua.edu.library.service;

import ua.edu.library.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    List<Author> getAuthorById(Integer id);

}
