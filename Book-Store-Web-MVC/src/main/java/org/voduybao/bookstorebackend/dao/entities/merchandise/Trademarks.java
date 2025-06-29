package org.voduybao.bookstorebackend.dao.entities.merchandise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.common.embedded.Address;
import org.voduybao.bookstorebackend.dao.entities.common.metadata.TimeStamped;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "trademarks")
public class Trademarks extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trademark_id")
    private Integer id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "origin", length = 255, nullable = false)
    private String origin;

    @Column(name = "contact", columnDefinition = "TEXT")
    private String contact;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @OneToMany(mappedBy = "trademark")
    @JsonIgnore
    private Set<ProductTrademark> products;

}
