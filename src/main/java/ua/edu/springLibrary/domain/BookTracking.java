package ua.edu.springLibrary.domain;

import lombok.Data;
import ua.edu.springLibrary.entity.BookEntity;
import ua.edu.springLibrary.entity.UserEntity;

import java.time.LocalDate;

@Data
public class BookTracking {

    private Integer id;

    private UserEntity userEntity;

    private BookEntity bookEntity;

    private LocalDate issue_date;

    private LocalDate return_date;

}
