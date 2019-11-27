package ua.edu.springLibrary.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.edu.springLibrary.entity.UserEntity;

import java.util.Optional;

@Repository("UserRepository")
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
