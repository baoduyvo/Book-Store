package org.voduybao.bookstorebackend.dao.entities.merchandise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.common.metadata.TimeStamped;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bundle_items")
public class ProductProductBundle extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bundle_item_id")
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bundle_id", nullable = false)
    @JsonIgnore
    private ProductBundle bundle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;
}
