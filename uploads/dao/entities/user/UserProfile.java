package org.voduybao.tools.dao.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.voduybao.tools.contants.e.GenderEnum;
import org.voduybao.tools.dao.entities.common.embedded.Address;
import org.voduybao.tools.dao.entities.common.metadata.TimeStamped;
import org.voduybao.tools.dao.entities.media.UserMedia;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_profile")
public class UserProfile extends TimeStamped {

    @Id
    private Integer userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<UserMedia> uploadedMedia;

    @Size(max = 50)
    @Column(name = "nickname", length = 50)
    private String nickname;

    @Size(max = 100)
    @Column(name = "intro", length = 100)
    private String intro;

    @Column(name = "marry")
    private Boolean marry;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    private GenderEnum gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "edu_id")
    private Education education;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "hobby_id")
    private Hobby hobby;

    @Embedded
    private Address address;
}
