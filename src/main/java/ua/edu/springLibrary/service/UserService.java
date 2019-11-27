package ua.edu.springLibrary.service;

import ua.edu.springLibrary.domain.User;

public interface UserService {
    User findUserByEmail(String email);

    void saveUser(User user);
}
