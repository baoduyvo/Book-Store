package org.voduybao.bookstorebackend.dao.repositories.merchandise;

import org.springframework.data.repository.CrudRepository;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Book;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Integer> {


}