package org.voduybao.bookstorebackend.dao.repositories.user;

import org.springframework.data.repository.CrudRepository;
import org.voduybao.bookstorebackend.dao.entities.user.Job;

public interface JobRepository extends CrudRepository<Job, Integer> {


}