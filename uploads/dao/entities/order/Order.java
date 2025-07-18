package org.voduybao.tools.dao.entities.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.tools.contants.e.StatusEnum;
import org.voduybao.tools.dao.entities.common.metadata.TimeStamped;
import org.voduybao.tools.dao.entities.transaction.Invoice;
import org.voduybao.tools.dao.entities.user.User;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "order_date")
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10)
    private StatusEnum status;

    @Column(name = "payment_method", length = 50)
    private String method;

    @Column(name = "billing_address", columnDefinition = "TEXT")
    private String billing;

    @Column(name = "shipping_address", columnDefinition = "TEXT")
    private String shipping;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private Set<OrderItem> orderOrderItems;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private Set<Invoice> invoices;
}
