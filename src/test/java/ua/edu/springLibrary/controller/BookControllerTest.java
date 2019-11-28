package ua.edu.springLibrary.controller;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.edu.springLibrary.service.BookService;
import org.springframework.data.domain.Pageable;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@WebAppConfiguration
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    BookService bookService;

    @InjectMocks
    BookController bookController;

    @org.junit.Test
    public void getAllBooksShouldReturnBookPage() {
        when(bookService.findPaginated(any(Pageable.class))).thenReturn(null);
        mvc.perform(MockMvcRequestBuilders
        .get("/book",1,10)
        .accept()
        );
    }
}