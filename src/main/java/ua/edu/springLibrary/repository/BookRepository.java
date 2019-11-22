package ua.edu.springLibrary.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ua.edu.springLibrary.entity.BookEntity;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Integer> {
    List<BookEntity> findAll (Pageable pageable);
}
