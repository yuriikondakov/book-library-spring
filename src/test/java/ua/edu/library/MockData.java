package ua.edu.library;

import ua.edu.library.domain.Author;
import ua.edu.library.domain.Role;
import ua.edu.library.domain.User;
import ua.edu.library.entity.AuthorEntity;
import ua.edu.library.entity.UserEntity;

public class MockData {
    public static final User MOCK_USER = initUser();
    public static final UserEntity MOCK_USER_ENTITY = initUserEntity();
    public static final Author MOCK_AUTHOR = initAuthor();
    public static final AuthorEntity MOCK_AUTHOR_ENTITY = initAuthorEntity();

    private static UserEntity initUserEntity() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setEmail("user@gmail.com");
        userEntity.setPassword("12345");
        userEntity.setName("John");
        userEntity.setRole(Role.USER);
        return userEntity;
    }

    private static User initUser() {
        return User.builder()
                .id(1)
                .email("user@gmail.com")
                .password("12345")
                .name("John")
                .role(Role.USER)
                .build();
    }

    private static Author initAuthor() {
        return new Author(1, "John", "Brown");
    }

    private static AuthorEntity initAuthorEntity() {
        final AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(1);
        authorEntity.setFirstName("John");
        authorEntity.setLastName("Brown");
        return authorEntity;
    }
}
