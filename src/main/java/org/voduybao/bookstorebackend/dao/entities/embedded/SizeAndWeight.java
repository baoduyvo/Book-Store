package org.voduybao.bookstorebackend.dao.entities.embedded;

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
public class SizeAndWeight {

    @Column(name = "dimensions", length = 50)
    private String dimensions;

    @Column(name = "weight")
    private Integer weight;
}