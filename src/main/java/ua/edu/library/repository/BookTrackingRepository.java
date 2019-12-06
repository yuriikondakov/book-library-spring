package ua.edu.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.library.entity.BookTrackingEntity;

import java.util.BitSet;

@Repository
public interface BookTrackingRepository extends JpaRepository<BookTrackingEntity, Integer> {

    Page<BookTrackingEntity> findAllByReturnDate(Object o, Pageable pageable);

    long countByReturnDate(Object o);
}
