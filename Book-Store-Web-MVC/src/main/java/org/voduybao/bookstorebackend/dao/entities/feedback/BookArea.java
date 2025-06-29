package org.voduybao.bookstorebackend.dao.entities.feedback;

import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Book;
import org.voduybao.bookstorebackend.dao.entities.user.User;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "book_areas")
public class BookArea {

    @Id
    private Integer userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", foreignKey = @ForeignKey(name = "fk_reviewer_book"))
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;
}