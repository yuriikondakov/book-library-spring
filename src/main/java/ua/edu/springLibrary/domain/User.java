package ua.edu.springLibrary.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class User {
    private Integer id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 5, max = 25, message = "About Me must be between 10 and 200 characters")
    private String password;

    private String phoneNumber;

    @NotNull(message = "Role cannot be null")
    private Role role;
}
