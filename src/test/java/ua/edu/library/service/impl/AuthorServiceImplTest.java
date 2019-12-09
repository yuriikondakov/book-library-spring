package ua.edu.library.service.impl;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.edu.library.domain.Author;
import ua.edu.library.entity.AuthorEntity;
import ua.edu.library.exception.EntityNotFoundException;
import ua.edu.library.mapper.AuthorMapper;
import ua.edu.library.repository.AuthorRepository;
import ua.edu.library.service.AuthorService;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static ua.edu.library.MockData.MOCK_AUTHOR;
import static ua.edu.library.MockData.MOCK_AUTHOR_ENTITY;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AuthorServiceImpl.class)
public class AuthorServiceImplTest {
    private static final Author AUTHOR = MOCK_AUTHOR;
    private static final AuthorEntity AUTHOR_ENTITY = MOCK_AUTHOR_ENTITY;
    private static final List<Author> AUTHOR_LIST = Collections.nCopies(3, MOCK_AUTHOR);
    private static final List<AuthorEntity> AUTHOR_ENTITY_LIST = Collections.nCopies(3, MOCK_AUTHOR_ENTITY);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private AuthorMapper authorMapper;

    @Autowired
    private AuthorService authorService;

    @After
    public void resetMocks() {
        reset(authorRepository, authorMapper);
    }


    @Test
    public void getAllShouldReturnAuthorsList() {
        when(authorRepository.findAll()).thenReturn(AUTHOR_ENTITY_LIST);
        when(authorMapper.mapAuthorEntityToAuthor(any(AuthorEntity.class))).thenReturn(AUTHOR);

        final List<Author> actual = authorService.getAll();

        assertThat(actual, is(AUTHOR_LIST));
        assertThat(actual, hasItem(AUTHOR));
    }

    @Test
    public void getAllShouldReturnEmptyAuthorsList() {
        when(authorRepository.findAll()).thenReturn(emptyList());
        when(authorMapper.mapAuthorEntityToAuthor(any(AuthorEntity.class))).thenReturn(AUTHOR);

        final List<Author> actual = authorService.getAll();

        assertThat(actual, is(emptyList()));
    }

    @Test
    public void getAuthorByIdShouldReturnAuthor() {
        when(authorRepository.findById(anyInt())).thenReturn(Optional.of(AUTHOR_ENTITY));
        when(authorMapper.mapAuthorEntityToAuthor(any(AuthorEntity.class))).thenReturn(AUTHOR);

        final List<Author> actual = authorService.getAuthorById(1);

        assertThat(actual, is(singletonList(AUTHOR)));
    }

    @Test
    public void getAuthorByIdShouldThrowException() {
        expectedException.expect(EntityNotFoundException.class);
        expectedException.expectMessage("Author not found");

        when(authorRepository.findById(anyInt())).thenReturn(Optional.empty());

        authorService.getAuthorById(5);
    }

    @Test
    public void save() {
    }

    @Test
    public void isAlreadyPresent() {
    }
}