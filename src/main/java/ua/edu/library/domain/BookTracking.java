package ua.edu.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTracking {
    private Integer id;

    @NotNull
    private User user;

    @NotNull
    private Book book;

    @NotNull
    private LocalDate issueDate;

    private LocalDate returnDate;
}
