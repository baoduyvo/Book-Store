package org.voduybao.bookstorebackend.dao.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.common.metadata.TimeStamped;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Book;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reading_history")
public class ReadingHistory extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Integer id;

    @Column(name = "progress", nullable = false)
    private Integer progress = 0;

    @Column(name = "last_read_at")
    private Date last;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
}
