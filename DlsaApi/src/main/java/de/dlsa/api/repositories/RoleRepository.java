package de.dlsa.api.repositories;

import de.dlsa.api.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRolename(String rolename);  // Sucht eine Rolle nach Name
}
