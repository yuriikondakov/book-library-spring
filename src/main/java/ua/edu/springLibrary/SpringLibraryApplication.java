package ua.edu.springLibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.edu.springLibrary.domain.User;

@SpringBootApplication(
        //exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
)
public class SpringLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLibraryApplication.class, args);
    }

}
