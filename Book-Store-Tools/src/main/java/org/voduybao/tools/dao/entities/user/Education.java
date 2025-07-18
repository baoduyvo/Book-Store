package org.voduybao.tools.dao.entities.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Education {
     int edu_id;
     String title;
     Instant created_at;
     Instant updated_at;
}
