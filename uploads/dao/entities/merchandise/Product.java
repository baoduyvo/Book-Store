package org.voduybao.tools.dao.entities.merchandise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.tools.dao.entities.common.metadata.TimeStamped;
import org.voduybao.tools.dao.entities.feedback.Rating;
import org.voduybao.tools.dao.entities.media.ProductMedia;
import org.voduybao.tools.dao.entities.order.CartItem;
import org.voduybao.tools.dao.entities.order.OrderItem;

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
    private Set<CategoryProduct> categories;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ProductProductBundle> bundles;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ProductMadeIn> madeIns;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ProductTrademark> trademarks;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ProductHashTag> hashTags;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ProductGroupOption> groups;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Variation> variations;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<OrderItem> orderOrderItems;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<CartItem> cartItems;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Rating> ratings;
}
