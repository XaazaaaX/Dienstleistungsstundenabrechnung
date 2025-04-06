package de.dlsa.api.controllers;

import de.dlsa.api.dtos.LoginUserDto;
import de.dlsa.api.dtos.RegisterUserDto;
import de.dlsa.api.responses.AuthResponse;
import de.dlsa.api.services.AuthenticationService;
import jakarta.validation.constraints.Null;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Verarbeitung von Anfragen für die Registrierung oder Anmeldung eines Users
 *
 * @author Benito Ernst
 * @version  01/2024
 */
@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private final AuthenticationService authenticationService;

    /**
     * Konstruktor
     *
     * @param authenticationService Service zur Weiterverarbeitung der Anfragen (AuthenticationService)
     */
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Endpunkt zum Authentifizierung eines Users
     *
     * @param registerUserDto Enthält die relevanten Userinformationen für die Registrierung (RegisterUserDto)
     * @return Rückgabe eines gültigen JWT zur Nutzung der geschützten Endpunkte der API
     */
    /*
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterUserDto registerUserDto) {
        return ResponseEntity.ok(authenticationService.register(registerUserDto));
    }
     */

    /**
     * Endpunkt zur Registrierung eines Users
     *
     * @param loginUserDto Enthält die relevanten Userinformationen für das Login (LoginUserDto)
     * @return Rückgabe eines gültigen JWT zur Nutzung der geschützten Endpunkte der API
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginUserDto loginUserDto) {
        return ResponseEntity.ok(authenticationService.login(loginUserDto));
    }
}

