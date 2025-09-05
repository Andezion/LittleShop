package org.leron.repository;

import org.leron.model.Role; // Убедитесь, что путь к вашей модели Role корректен
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findByName(String name);
}