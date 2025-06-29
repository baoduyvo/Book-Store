package org.voduybao.bookstorebackend.dao.repositories.merchandise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {


}