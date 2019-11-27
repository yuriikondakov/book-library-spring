package ua.edu.springLibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class Book {

    private Integer id;

    @NotNull(message = "Authors cannot be null")
    private List<Author> authors;

    @NotNull(message = "Name cannot be null")
    private String name;

    private String description;

    private Integer shelfId;

}
