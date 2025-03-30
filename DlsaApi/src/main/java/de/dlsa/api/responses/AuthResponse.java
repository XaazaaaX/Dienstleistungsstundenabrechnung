package de.dlsa.api.responses;

/**
 * Klasse zur Darstellung des JWT bei Rückgabe aus der Registrierung ode dem Login
 *
 * @author Benito Ernst
 * @version  01/2024
 */
public class AuthResponse {
    private String token;

    public String getToken() {
        return token;
    }

    /**
     * Setter für den JWT
     *
     * @param token Jwt
     * @return Gibt die aktuelle Instanz der Klasse zurück
     */
    public AuthResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
