package ua.edu.springLibrary.mapper;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.edu.springLibrary.domain.BookTracking;
import ua.edu.springLibrary.entity.BookTrackingEntity;

@Slf4j
@Component
public class BookTrackingMapper {
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    @Autowired
    public BookTrackingMapper(UserMapper userMapper, BookMapper bookMapper) {
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
    }

    public BookTracking mapBookTrackingEntityToBookTracking(BookTrackingEntity bookTrackingEntity) {
        return new BookTracking(
                bookTrackingEntity.getId(),
                userMapper.mapUserEntityToUser(bookTrackingEntity.getUserEntity()),
                bookMapper.mapBookEntityToBook(bookTrackingEntity.getBookEntity()),
                bookTrackingEntity.getIssue_date(),
                bookTrackingEntity.getReturnDate()
        );
    }

    public BookTrackingEntity mapBookTrackingToBookTrackingEntity(BookTracking bookTracking) {
        BookTrackingEntity bookTrackingEntity = new BookTrackingEntity();
        bookTrackingEntity.setUserEntity(userMapper.mapUserToUserEntity(bookTracking.getUser()));
        bookTrackingEntity.setBookEntity(bookMapper.mapBookToBookEntity(bookTracking.getBook()));
        bookTrackingEntity.setIssue_date(bookTracking.getIssue_date());
        bookTrackingEntity.setReturnDate(bookTracking.getReturn_date());
        log.debug(bookTrackingEntity.toString());
        return bookTrackingEntity;
    }
}
