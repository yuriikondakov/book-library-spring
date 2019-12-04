package ua.edu.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.library.entity.BookTrackingEntity;

@Repository
public interface BookTrackingRepository extends JpaRepository<BookTrackingEntity, Integer> {

}
