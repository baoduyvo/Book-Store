package org.voduybao.bookstorebackend.dao.entities.common.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileEntry {

    @Column(name = "width", columnDefinition = "DOUBLE PRECISION DEFAULT 0")
    private Double width = 0.0;

    @Column(name = "height", columnDefinition = "DOUBLE PRECISION DEFAULT 0")
    private Double height = 0.0;

    @Column(name = "duration",columnDefinition = "DOUBLE PRECISION DEFAULT 0")
    private Double duration = 0.0;

    @Column(name = "size",columnDefinition = "BIGINT DEFAULT 0")
    private Long size = 0L;
}