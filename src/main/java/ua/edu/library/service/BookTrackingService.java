package ua.edu.library.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.edu.library.domain.BookTracking;
import ua.edu.library.domain.User;


public interface BookTrackingService {
    void saveBookTracking(User user, Integer bookId);

    void returnBook(Integer bookTrackingId);

    BookTracking findById(Integer bookTrackingId);

    Page<BookTracking> getRegister(Pageable pageable);
}
