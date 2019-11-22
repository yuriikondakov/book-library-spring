package ua.edu.springLibrary.repository;

import org.springframework.data.repository.CrudRepository;
import ua.edu.springLibrary.entity.AuthorEntity;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Integer> {
}
