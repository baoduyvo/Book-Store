package org.voduybao.tools.dao.repository.auth;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.voduybao.tools.dao.entities.auth.Token;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, Integer> {
    @Query("SELECT t FROM Token t WHERE t.jti = :jti")
    Optional<Token> findByJti(@Param("jti") String jti);

}