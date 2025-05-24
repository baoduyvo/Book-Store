package org.voduybao.bookstorebackend.dao.repositories.auth;

import org.springframework.data.repository.CrudRepository;
import org.voduybao.bookstorebackend.dao.entities.auth.Permission;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {

}