package ua.edu.springLibrary.mapper;

import org.springframework.stereotype.Component;
import ua.edu.springLibrary.domain.User;
import ua.edu.springLibrary.entity.BookTrackingEntity;
import ua.edu.springLibrary.entity.UserEntity;

import java.util.stream.Collectors;

@Component("UserMapper")
public class UserMapper {

    public User mapUserEntityToUser(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getPhoneNumber(),
                userEntity.getRole(),
                userEntity.getBookTrackingEntityList().stream()
                        .filter(b -> b.getReturnDate() == null)
                        .map(BookTrackingEntity::getId)
                        .collect(Collectors.toList()),
                userEntity.getBookTrackingEntityList().stream()
                        .filter(b -> b.getReturnDate() == null)
                        .map(bookTrackingEntity -> bookTrackingEntity.getBookEntity().getId())
                        .collect(Collectors.toList())
        );
    }

    public UserEntity mapUserToUserEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getRole(),
                null);
    }
}
