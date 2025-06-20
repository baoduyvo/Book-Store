package org.voduybao.tools.dao.entities.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.common.metadata.TimeStamped;
import org.voduybao.bookstorebackend.dao.entities.transaction.Invoice;
import org.voduybao.bookstorebackend.dao.entities.user.User;
import org.voduybao.bookstorebackend.tools.contants.e.StatusEnum;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transactions")
public class Transaction extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer id;

    @Column(name = "payment_method", length = 50)
    private String method;

    @Column(name = "total_amount", nullable = false)
    private Integer total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10)
    private StatusEnum status;

    @Column(name = "transaction_reference", length = 255)
    private String reference;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    @JsonIgnore
    private Invoice invoice;
}
