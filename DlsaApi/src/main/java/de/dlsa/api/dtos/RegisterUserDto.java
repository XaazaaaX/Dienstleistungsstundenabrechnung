package de.dlsa.api.dtos;

/**
 * Klasse zur Übertragung der Registrierungsdaten vom Client zum Server
 *
 * @author Benito Ernst
 * @version  01/2024
 */
public class RegisterUserDto {
    private String username;

    private String password;

    RegisterUserDto(){}

    /**
     * Getter für den Username
     *
     * @return Gibt den Wert aus dem Attribut "username" zurück
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter für das Passwort
     *
     * @return Gibt den Wert aus dem Attribut "password" zurück
     */
    public String getPassword() {
        return password;
    }
}
