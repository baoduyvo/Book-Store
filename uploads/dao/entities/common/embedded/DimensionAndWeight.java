package org.voduybao.tools.dao.entities.common.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DimensionAndWeight {

    @Column(name = "dimensions", length = 50)
    private String dimensions;

    @Column(name = "weight")
    private Integer weight;
}