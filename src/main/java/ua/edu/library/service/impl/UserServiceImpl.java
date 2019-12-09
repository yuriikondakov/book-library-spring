package ua.edu.library.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.edu.library.domain.Role;
import ua.edu.library.domain.User;
import ua.edu.library.mapper.UserMapper;
import ua.edu.library.repository.UserRepository;
import ua.edu.library.service.UserService;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::mapUserEntityToUser)
                .orElse(null);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(userMapper.mapUserToUserEntity(user));
    }
}
