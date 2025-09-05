package org.leron.service;

import org.leron.model.User;
import org.leron.model.Role; // Убедитесь, что импорт Role корректен
import org.leron.repository.UserRepository;
import org.leron.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Важно для хэширования паролей
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Для транзакционности

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public User registerNewUser(String username, String password, String email) {
        if (existsByUsername(username)) {
            throw new RuntimeException("A user with this name already exists: " + username);
        }
        if (existsByEmail(email)) {
            throw new RuntimeException("A user with this email address already exists: " + email);
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRegistrationDate(LocalDateTime.now());

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    Role role = new Role("ROLE_USER");
                    return roleRepository.save(role);
                });

        newUser.setRoles(Collections.singleton(userRole));

        return userRepository.save(newUser);
    }

    @Transactional
    public User createAdminUserIfNotExist(String username, String password, String email) {
        if (userRepository.findByUsername(username).isEmpty()) {
            User adminUser = new User();
            adminUser.setUsername(username);
            adminUser.setEmail(email);
            adminUser.setPassword(passwordEncoder.encode(password));
            adminUser.setRegistrationDate(LocalDateTime.now());

            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> {
                        Role role = new Role("ROLE_ADMIN");
                        return roleRepository.save(role);
                    });

            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseGet(() -> {
                        Role role = new Role("ROLE_USER");
                        return roleRepository.save(role);
                    });

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            roles.add(userRole);
            adminUser.setRoles(roles);

            return userRepository.save(adminUser);
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}