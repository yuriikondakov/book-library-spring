package ua.edu.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.library.domain.Author;
import ua.edu.library.entity.AuthorEntity;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
    Optional<AuthorEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
