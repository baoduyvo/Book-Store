package org.voduybao.tools.dao.repository.auth;

import org.springframework.data.repository.CrudRepository;
import org.voduybao.tools.dao.entities.auth.Permission;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {

}