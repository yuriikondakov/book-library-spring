package ua.edu.library.mapper;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.edu.library.domain.BookTracking;
import ua.edu.library.entity.BookTrackingEntity;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookTrackingMapper {
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    public BookTracking mapBookTrackingEntityToBookTracking(BookTrackingEntity bookTrackingEntity) {
        return new BookTracking(
                bookTrackingEntity.getId(),
                userMapper.mapUserEntityToUser(bookTrackingEntity.getUserEntity()),
                bookMapper.mapBookEntityToBook(bookTrackingEntity.getBookEntity()),
                bookTrackingEntity.getIssueDate(),
                bookTrackingEntity.getReturnDate()
        );
    }

    public BookTrackingEntity mapBookTrackingToBookTrackingEntity(BookTracking bookTracking) {
        BookTrackingEntity bookTrackingEntity = new BookTrackingEntity();
        bookTrackingEntity.setUserEntity(userMapper.mapUserToUserEntity(bookTracking.getUser()));
        bookTrackingEntity.setBookEntity(bookMapper.mapBookToBookEntity(bookTracking.getBook()));
        bookTrackingEntity.setIssueDate(bookTracking.getIssueDate());
        bookTrackingEntity.setReturnDate(bookTracking.getReturnDate());
        log.debug(bookTrackingEntity.toString());
        return bookTrackingEntity;
    }
}
