package ua.edu.library.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.edu.library.domain.BookTracking;
import ua.edu.library.domain.User;
import ua.edu.library.entity.BookTrackingEntity;
import ua.edu.library.mapper.BookTrackingMapper;
import ua.edu.library.repository.BookRepository;
import ua.edu.library.repository.BookTrackingRepository;
import ua.edu.library.repository.UserRepository;
import ua.edu.library.service.BookService;
import ua.edu.library.service.BookTrackingService;

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
        BookTrackingEntity bookTrackingEntity = new BookTrackingEntity();
        bookTrackingEntity.setUserEntity(userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id")));
        bookTrackingEntity.setBookEntity(bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id")));
        bookTrackingEntity.setIssue_date(LocalDate.now());
        bookTrackingRepository.save(bookTrackingEntity);
    }

    @Override
    public void returnBook(Integer bookTrackingId) {
        BookTrackingEntity bookTrackingEntity = bookTrackingRepository.findById(bookTrackingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bookTracking Id"));
        bookTrackingEntity.setReturnDate(LocalDate.now());
        bookTrackingRepository.save(bookTrackingEntity);
    }

    @Override
    public BookTracking findById(Integer bookTrackingId) {
        return bookTrackingRepository.findById(bookTrackingId)
                .map(bookTrackingMapper::mapBookTrackingEntityToBookTracking)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bookTracking Id"));
    }
}
