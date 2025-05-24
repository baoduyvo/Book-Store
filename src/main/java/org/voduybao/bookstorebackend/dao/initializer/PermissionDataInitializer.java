package org.voduybao.bookstorebackend.dao.initializer;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.auth.Permission;
import org.voduybao.bookstorebackend.dao.repositories.auth.PermissionRepository;

@Component
public class PermissionDataInitializer {

    private final PermissionRepository permissionRepository;

    public PermissionDataInitializer(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @PostConstruct
    public void initPermissions() {
        if (permissionRepository.count() == 0) {
            createPermission("view_books", "Permission to view books");
            createPermission("manage_books", "Permission to create, update, delete books");
            createPermission("view_orders", "Permission to view orders");
            createPermission("manage_orders", "Permission to confirm or cancel orders");
            createPermission("manage_users", "Permission to create, update, delete users");
        }
    }

    private void createPermission(String name, String description) {
        Permission permission = new Permission();
        permission.setPermissionName(name);
        permission.setDescription(description);
        permissionRepository.save(permission);
    }
}