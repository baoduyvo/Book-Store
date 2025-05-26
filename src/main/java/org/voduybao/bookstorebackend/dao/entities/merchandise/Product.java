package org.voduybao.bookstorebackend.dao.entities.merchandise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.common.metadata.TimeStamped;
import org.voduybao.bookstorebackend.dao.entities.media.ProductMedia;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "product_type", length = 50)
    private String producType;

    @OneToMany(mappedBy = "product")
    private Set<ProductMedia> productMedia;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<CategoryProduct> products;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Book> books;
}
