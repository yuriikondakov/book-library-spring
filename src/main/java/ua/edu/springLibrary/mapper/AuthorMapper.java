package ua.edu.springLibrary.mapper;

import org.springframework.stereotype.Component;
import ua.edu.springLibrary.domain.Author;
import ua.edu.springLibrary.entity.AuthorEntity;

@Component
public class AuthorMapper {
    public Author mapAuthorEntityToAuthor(AuthorEntity authorEntity) {
        return new Author(authorEntity.getId(), authorEntity.getFirstName(), authorEntity.getLastName());
    }

    AuthorEntity mapAuthorToAuthorEntity(Author author) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(author.getId());
        authorEntity.setFirstName(author.getFirstName());
        authorEntity.setLastName(author.getLastName());
        return authorEntity;
    }
}
