package org.voduybao.tools.dao.entities.location;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Commune {
     Long id;
     Long province_id;
     Long district_id;
     String code;
     String name;
     String slug;
     Integer level;
}
