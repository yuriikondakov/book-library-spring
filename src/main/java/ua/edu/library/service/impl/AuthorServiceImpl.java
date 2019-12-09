package ua.edu.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.library.domain.Author;
import ua.edu.library.exception.EntityNotFoundException;
import ua.edu.library.mapper.AuthorMapper;
import ua.edu.library.repository.AuthorRepository;
import ua.edu.library.service.AuthorService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::mapAuthorEntityToAuthor)
                .collect(Collectors.toList());
    }

    @Override
    public List<Author> getAuthorById(Integer id) {
        Author author = authorRepository.findById(id)
                .map(authorMapper::mapAuthorEntityToAuthor)
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        return Collections.singletonList(author);
    }

    @Override
    public boolean save(Author author) {
        if (isAlreadyPresent(author)) {
            return false;
        }
        authorRepository.save(authorMapper.mapAuthorToAuthorEntity(author));
        return true;
    }

    @Override
    public boolean isAlreadyPresent(Author author) {
        return authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName()).isPresent();
    }
}
