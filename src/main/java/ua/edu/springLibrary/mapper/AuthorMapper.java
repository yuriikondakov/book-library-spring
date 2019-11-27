package ua.edu.springLibrary.mapper;

import org.springframework.stereotype.Component;
import ua.edu.springLibrary.domain.Author;
import ua.edu.springLibrary.entity.AuthorEntity;

@Component
public class AuthorMapper {
    public Author mapAuthorEntityToAuthor(AuthorEntity authorEntity) {
        return new Author(authorEntity.getId(), authorEntity.getFirstName(), authorEntity.getLastName());
    }

    public AuthorEntity mapAuthorToAuthorEntity(Author author) {
        return new AuthorEntity(author.getId(), author.getFirstName(), author.getLastName());
    }
}
