package ua.edu.library.service.impl;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.edu.library.domain.Role;
import ua.edu.library.domain.User;
import ua.edu.library.entity.UserEntity;
import ua.edu.library.mapper.UserMapper;
import ua.edu.library.repository.UserRepository;
import ua.edu.library.service.UserService;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static ua.edu.library.MockData.MOCK_USER;
import static ua.edu.library.MockData.MOCK_USER_ENTITY;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UserServiceImpl.class)
public class UserServiceImplTest {
    private static final User USER = MOCK_USER;
    private static final UserEntity USER_ENTITY = MOCK_USER_ENTITY;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @After
    public void resetMocks() {
        reset(userRepository, bCryptPasswordEncoder, userMapper);
    }

    @Test
    public void findUserByEmailShouldReturnUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(USER_ENTITY));
        when(userMapper.mapUserEntityToUser(any(UserEntity.class))).thenReturn(USER);

        final User actual = userService.findUserByEmail("user@gmail.com");

        assertThat(actual.getEmail(), is(USER.getEmail()));
        assertThat(actual.getRole(), is(Role.USER));
    }

    @Test
    public void findUserByEmailShouldReturnNull() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userMapper.mapUserEntityToUser(any(UserEntity.class))).thenReturn(USER);

        final User actual = userService.findUserByEmail("admin@gmail.com");

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void saveUserShouldSaveUser() {
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encryptPassword");
        when(userMapper.mapUserToUserEntity(any(User.class))).thenReturn(USER_ENTITY);

        userService.saveUser(USER);

        verify(userRepository).save(any(UserEntity.class));
    }
}