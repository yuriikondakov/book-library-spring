package ua.edu.library.service;

import ua.edu.library.domain.User;

public interface UserService {
    User findUserByEmail(String email);

    void saveUser(User user);
}
