package ua.edu.springLibrary.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;
import ua.edu.springLibrary.domain.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="role",  nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "userEntity")
    private List<BookTrackingEntity> bookTrackingEntityList;
}
