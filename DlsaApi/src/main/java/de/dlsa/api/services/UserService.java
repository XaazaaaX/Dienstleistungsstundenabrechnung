package de.dlsa.api.services;

import de.dlsa.api.entities.Role;
import de.dlsa.api.repositories.RoleRepository;
import de.dlsa.api.repositories.UserRepository;
import de.dlsa.api.entities.User;
import jakarta.validation.ConstraintViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> createUser(List<User> users) {

        for (User user: users) {

            /*if (userRepository.existsByUsername(user.getUsername())) {
                throw new RuntimeException("Benutzername bereits vergeben: " + user.getUsername());
            }

             */

            Role role = roleRepository.findByRolename(user.getRole().getRolename())
                    .orElseThrow(() -> new RuntimeException("Rolle nicht gefunden: " + user.getRole().getRolename()));

            user.setRole(role);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.saveAll(users);
    }

    public User updateUser(int id, User userDetails) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
/*
        existing.setName(userDetails.getName());
        existing.setEmail(userDetails.getEmail());


         */

        return userRepository.save(existing);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
