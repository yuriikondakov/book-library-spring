package ua.edu.springLibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 5, max = 25, message = "Password must be between 5 and 25 characters")
    private String password;

    private String phoneNumber;

    @NotNull(message = "Role cannot be null")
    private Role role;

    private List<Integer> bookTrackingIds;

    private List<Integer> bookIds;
}
