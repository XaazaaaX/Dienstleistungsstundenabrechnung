package de.dlsa.api.repositories;

import de.dlsa.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Interface zur Erweiterung der User Query-Methoden des JpaRepositories
 *
 * @author Benito Ernst
 * @version  01/2024
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

}