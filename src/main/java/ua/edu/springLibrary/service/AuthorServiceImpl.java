package ua.edu.springLibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.springLibrary.domain.Author;
import ua.edu.springLibrary.mapper.AuthorMapper;
import ua.edu.springLibrary.repository.AuthorRepository;

import java.util.ArrayList;
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
}
