package de.dlsa.api.config;

import de.dlsa.api.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Klasse zur Konfiguration der Api
 *
 * @author Benito Ernst
 * @version  01/2024
 */
@Configuration
public class ApplicationConfiguration {
    private final UserRepository userRepository;

    /**
     * Konstruktor
     *
     * @param userRepository Repo für den DB Zugriff
     */
    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Erstellt eine Instanz von UserDetailsService mit der zusätzlichen Funktion "findByUsername"
     *
     * @return Instanz von UserDetailsService
     */
    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Erstellt eine Instanz von BCryptPasswordEncoder
     *
     * @return Instanz von BCryptPasswordEncoder
     */
    /*@Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Sha256PasswordEncoder();
    }

    /**
     * Erstellt eine Instanz von BCryptPasswordEncoder
     *
     * @param config AuthenticationConfiguration
     * @return Instanz von BCryptPasswordEncoder
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Erstellt eine Instanz von AuthenticationProvider und konfiguriert diesen
     *
     * @return Instanz von AuthenticationProvider
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    /**
     * Erstellt eine Instanz von ModelMapper
     *
     * @return Instanz von ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper;
    }
}
