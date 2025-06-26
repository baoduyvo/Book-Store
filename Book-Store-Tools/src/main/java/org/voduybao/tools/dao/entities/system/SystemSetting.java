package org.voduybao.tools.dao.entities.system;

import jakarta.persistence.*;
import lombok.*;
import org.voduybao.tools.dao.entities.common.metadata.TimeStamped;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "system_settings")
public class SystemSetting extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "setting_key")
    private String id;


    @Column(name = "setting_value", columnDefinition = "TEXT")
    private String value;
}
