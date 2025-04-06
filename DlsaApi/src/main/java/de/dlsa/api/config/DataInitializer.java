package de.dlsa.api.config;

import de.dlsa.api.entities.Role;
import de.dlsa.api.entities.User;
import de.dlsa.api.repositories.RoleRepository;
import de.dlsa.api.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        initilizeRoles();
        initilizeUser();

    }

    private void initilizeRoles(){
        List<String> standardRoles = List.of("Administrator", "Benutzer", "Gast");

        for (String roleName : standardRoles) {
            roleRepository.findByRolename(roleName)
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setRolename(roleName);
                        return roleRepository.save(newRole);
                    });
        }

        System.out.println("Rollen wurden initialisiert.");
    }

    private void initilizeUser() {
        // Map mit username → rolename
        Map<String, String> standardUsers = Map.of(
                "admin", "Administrator",
                "max", "Benutzer",
                "lisa", "Gast"
        );

        for (Map.Entry<String, String> entry : standardUsers.entrySet()) {
            String username = entry.getKey();
            String rolename = entry.getValue();

            userRepository.findByUsername(username)
                    .orElseGet(() -> {
                        Role role = roleRepository.findByRolename(rolename)
                                .orElseThrow(() -> new RuntimeException("Rolle nicht gefunden: " + rolename));

                        User newUser = new User()
                                .setUsername(username)
                                .setPassword(passwordEncoder.encode("admin")) // Standardpasswort
                                .setActive(true)
                                .setRole(role);
                        return userRepository.save(newUser);
                    });
        }

        System.out.println("User wurden initialisiert.");
    }
}
