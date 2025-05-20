package org.voduybao.bookstorebackend.dao.initializer;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.Permission;
import org.voduybao.bookstorebackend.dao.entities.Role;
import org.voduybao.bookstorebackend.dao.repositories.PermissionRepository;
import org.voduybao.bookstorebackend.dao.repositories.RoleRepository;
import org.voduybao.bookstorebackend.tools.contants.RoleEnum;

import java.util.HashSet;
import java.util.Set;

@Component
public class RoleDataInitializer {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleDataInitializer(RoleRepository roleRepository,
                               PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @PostConstruct
    public void initRoles() {
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setRoleName(RoleEnum.ADMIN);
            adminRole.setDescription("Administrator role");
            Set<Permission> adminPermissions = new HashSet<>();
            permissionRepository.findAll().forEach(adminPermissions::add);
            adminRole.setPermissions(adminPermissions);
            roleRepository.save(adminRole);

            createRole(RoleEnum.CUSTOMER, "Customer role");
        }
    }

    private void createRole(RoleEnum roleName, String description) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleRepository.save(role);
    }
}