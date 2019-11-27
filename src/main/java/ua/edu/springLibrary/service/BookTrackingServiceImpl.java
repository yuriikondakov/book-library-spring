package ua.edu.springLibrary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.springLibrary.domain.Book;
import ua.edu.springLibrary.domain.BookTracking;
import ua.edu.springLibrary.domain.User;
import ua.edu.springLibrary.entity.BookTrackingEntity;
import ua.edu.springLibrary.mapper.BookTrackingMapper;
import ua.edu.springLibrary.repository.BookRepository;
import ua.edu.springLibrary.repository.BookTrackingRepository;
import ua.edu.springLibrary.repository.UserRepository;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookTrackingServiceImpl implements BookTrackingService {
    private final BookTrackingRepository bookTrackingRepository;
    private final BookTrackingMapper bookTrackingMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookService bookService;

    @Override
    public void saveBookTracking(User user, Integer bookId) {

        BookTracking bookTracking = new BookTracking();
        bookTracking.setUser(user);
        bookTracking.setBook(bookService.findById(bookId));
        bookTracking.setIssue_date(LocalDate.now());
        log.debug(bookTracking.toString());
        bookTrackingRepository.save(bookTrackingMapper.mapBookTrackingToBookTrackingEntity(bookTracking));
    }

    @Override
    public BookTracking findByUserAndBook(User user, Book book) {
        BookTrackingEntity bookTrackingEntity = bookTrackingRepository
                .findByUserEntityAndBookEntity(userRepository.findById(user.getId()).orElse(null),
                        bookRepository.findById(book.getId()).orElse(null))
                .orElseThrow(() -> new IllegalArgumentException("Invalid parameters bookTracking"));
        return bookTrackingMapper.mapBookTrackingEntityToBookTracking(bookTrackingEntity);
    }

    @Override
    public void returnBook(Integer bookTrackingId) {
        BookTrackingEntity bookTrackingEntity = bookTrackingRepository.findById(bookTrackingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bookTracking Id"));
        bookTrackingEntity.setReturnDate(LocalDate.now());
        bookTrackingRepository.save(bookTrackingEntity);
    }
}
