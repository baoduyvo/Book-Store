package org.voduybao.bookstorebackend.dao.repositories.merchandise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voduybao.bookstorebackend.dao.entities.merchandise.MadeIn;

public interface MadeInRepository extends JpaRepository<MadeIn, Integer> {


}