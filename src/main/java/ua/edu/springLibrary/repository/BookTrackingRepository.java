package ua.edu.springLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.springLibrary.domain.BookTracking;
import ua.edu.springLibrary.entity.BookEntity;
import ua.edu.springLibrary.entity.BookTrackingEntity;
import ua.edu.springLibrary.entity.UserEntity;

import java.util.Optional;

@Repository
public interface BookTrackingRepository extends JpaRepository<BookTrackingEntity, Integer> {
    Optional<BookTrackingEntity> findByUserEntityAndBookEntity(UserEntity userEntity, BookEntity bookEntity);

}
