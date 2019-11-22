package ua.edu.springLibrary.repository;

import org.springframework.data.repository.CrudRepository;
import ua.edu.springLibrary.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
