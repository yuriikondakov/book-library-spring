package ua.edu.springLibrary.service;

import ua.edu.springLibrary.domain.Book;
import ua.edu.springLibrary.domain.BookTracking;
import ua.edu.springLibrary.domain.User;

public interface BookTrackingService {
    void saveBookTracking(User user, Integer bookId);

    void returnBook(Integer bookTrackingId);

    BookTracking findById(Integer bookTrackingId);
}
