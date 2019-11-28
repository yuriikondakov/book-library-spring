package ua.edu.springLibrary.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class BookEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private List<AuthorEntity> authors;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false, length = 2048, columnDefinition = "text")
    private String description;

    @Column(name = "shelfId")
    private Integer shelfId;

    @OneToMany(mappedBy = "bookEntity")
    private List<BookTrackingEntity> bookTrackingList;
}
