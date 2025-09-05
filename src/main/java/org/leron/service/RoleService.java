package org.leron.service;

import org.leron.model.Role;
import org.leron.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void createDefaultRolesIfNotExist() {
        if (findByName("ROLE_USER").isEmpty()) {
            saveRole(new Role("ROLE_USER"));
            System.out.println("Role ROLE_USER created.");
        }
        if (findByName("ROLE_ADMIN").isEmpty()) {
            saveRole(new Role("ROLE_ADMIN"));
            System.out.println("Role ROLE_ADMIN created.");
        }
    }
}