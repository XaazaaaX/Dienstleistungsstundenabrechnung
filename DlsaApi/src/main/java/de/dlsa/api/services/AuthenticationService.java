package de.dlsa.api.services;

import de.dlsa.api.dtos.RegisterUserDto;
import de.dlsa.api.dtos.LoginUserDto;
import de.dlsa.api.entities.User;
import de.dlsa.api.repositories.UserRepository;
import de.dlsa.api.responses.AuthResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Weiterverarbeitung der Anfragen aus dem AuthController
 *
 * @author Benito Ernst
 * @version  01/2024
 */
@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    /**
     * Konstruktor
     *
     * @param userRepository Objekt für den Zugriff auf die User Tabelle in der DB (UserRepository)
     * @param authenticationManager SpringBoot Objekt zur Authentifizierung des User  (AuthenticationManager)
     * @param passwordEncoder Objekt zum Ver- und Entschlüsseln von Userpasswötern (PasswordEncoder)
     * @param jwtService Objekt verwalten von JWT (JwtService)
     */
    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     * Registriert einen User und speichert ihn in der DB
     *
     * @param input Zu registrierender User (RegisterUserDto)
     * @return Rückgabe eines gültigen JWT zur Nutzung der geschützten Endpunkte der API
     */
    public AuthResponse register(RegisterUserDto input) {
        User user = new User()
                .setUsername(input.getUsername())
                .setPassword(passwordEncoder.encode(input.getPassword()));


        userRepository.save(user);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        User dbUser = userRepository.findByUsername(input.getUsername()).orElseThrow();

        return handleJwt(dbUser);
    }

    /**
     * Prüft die Credentials eines registrierten Users
     *
     * @param input Einzulogender User (LoginUserDto)
     * @return Rückgabe eines gültigen JWT zur Nutzung der geschützten Endpunkte der API
     */
    public AuthResponse login(LoginUserDto input) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        User user = userRepository.findByUsername(input.getUsername()).orElseThrow();

        return handleJwt(user);
    }

    /**
     * Generiert einen userbezogenen JWT mithilfe des JwtService
     *
     * @param authenticatedUser Aktueller User (User)
     * @return Rückgabe eines gültigen JWT zur Nutzung
     */
    private AuthResponse handleJwt(User authenticatedUser){

        String accessToken = jwtService.generateToken(authenticatedUser);

        return new AuthResponse().setToken(accessToken);
    }
}
