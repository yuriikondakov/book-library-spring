package ua.edu.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Integer id;

    @NotNull(message = "Authors cannot be null")
    private List<Author> authors;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    private String description;

    private Integer shelfId;

}
