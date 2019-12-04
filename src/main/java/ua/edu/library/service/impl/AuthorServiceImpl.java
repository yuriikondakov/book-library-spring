package ua.edu.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.library.domain.Author;
import ua.edu.library.mapper.AuthorMapper;
import ua.edu.library.repository.AuthorRepository;
import ua.edu.library.service.AuthorService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<Author> getAll() {
        List<Author> authors = new ArrayList<>();
        authorRepository.findAll()
                .forEach(authorEntity -> authors.add(authorMapper.mapAuthorEntityToAuthor(authorEntity)));
        return authors;
    }

    @Override
    public List<Author> getAuthorById(Integer id) {
        return Collections.singletonList(authorRepository.findById(id)
                .map(authorMapper::mapAuthorEntityToAuthor)
                .orElse(null));
    }
}
