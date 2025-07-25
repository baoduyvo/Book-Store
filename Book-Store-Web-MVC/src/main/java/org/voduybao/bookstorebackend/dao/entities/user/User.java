package org.voduybao.bookstorebackend.dao.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.feedback.Activation;
import org.voduybao.bookstorebackend.dao.entities.feedback.Rating;
import org.voduybao.bookstorebackend.dao.entities.auth.Role;
import org.voduybao.bookstorebackend.dao.entities.auth.Token;
import org.voduybao.bookstorebackend.dao.entities.common.metadata.TimeStamped;
import org.voduybao.bookstorebackend.dao.entities.feedback.Notification;
import org.voduybao.bookstorebackend.dao.entities.feedback.ReviewerArea;
import org.voduybao.bookstorebackend.dao.entities.order.Cart;
import org.voduybao.bookstorebackend.dao.entities.order.Order;
import org.voduybao.bookstorebackend.dao.entities.transaction.Invoice;
import org.voduybao.bookstorebackend.dao.entities.transaction.Transaction;
import org.voduybao.bookstorebackend.tools.contants.e.AuthProviderEnum;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_unique_email_phone_index",
                        columnNames = {"email", "phone_number"}
                )
        })
public class User extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "phone_number", unique = true, length = 20)
    private String phoneNumber;

    @Column(name = "email", unique = true, length = 255)
    private String email;

    @Column(name = "password_hash", length = 255)
    private String password;

    @Column(name = "auth_provider")
    @Enumerated(EnumType.STRING)
    private AuthProviderEnum authProvider;

    @Column(name = "provider_id", length = 255)
    private String providerId;

    @Column(name = "is_active")
    private Boolean isActive = false;

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private Token token;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private UserProfile profile;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<ReadingHistory> readingHistories;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<ListeningHistory> listeningHistories;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Order> orders;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Cart> carts;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Rating> ratings;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private ReviewerArea reviewerArea;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Activation> activations;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Invoice> invoices;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Transaction> transactions;
}
