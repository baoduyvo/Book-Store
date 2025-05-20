package org.voduybao.bookstorebackend.dao.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.embedded.Address;
import org.voduybao.bookstorebackend.tools.contants.GenderEnum;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_profile")
public class UserProfile {

    @Id
    private Integer userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "profile_image", columnDefinition = "TEXT")
    private String profileImage;

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

    @Column(name = "hobby_id")
    private Integer hobbyId;

    @Column(name = "job_id")
    private Integer jobId;

    @Column(name = "edu_id")
    private Integer eduId;

    @Embedded
    private Address address;
}
