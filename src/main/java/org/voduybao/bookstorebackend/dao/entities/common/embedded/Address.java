package org.voduybao.bookstorebackend.dao.entities.common.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Column(name = "address")
    private String address;

    @Size(max = 100)
    @Column(name = "city", length = 100)
    private String city;

    @Size(max = 100)
    @Column(name = "state", length = 100)
    private String state;

    @Size(max = 100)
    @Column(name = "country", length = 100)
    private String country;

    @Size(max = 20)
    @Column(name = "postal_code", length = 20)
    private String postalCode;
}