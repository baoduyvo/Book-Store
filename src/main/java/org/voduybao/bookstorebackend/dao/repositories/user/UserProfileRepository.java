package org.voduybao.bookstorebackend.dao.repositories.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.voduybao.bookstorebackend.dao.entities.user.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {

    @Query(value = "update UserProfile p set p.firstName = :image where p.userId =:userID")
    void updateAvatar(@Param("image") String image, @Param("userID") int userID);
}