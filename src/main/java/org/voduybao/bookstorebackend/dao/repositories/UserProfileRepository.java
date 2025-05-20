package org.voduybao.bookstorebackend.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.voduybao.bookstorebackend.dao.entities.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {
}