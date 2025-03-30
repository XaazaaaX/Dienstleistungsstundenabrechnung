package de.dlsa.api.responses;

/**
 * Klasse zur Darstellung einer User Response bei Rückgabe aus der Level-Anfrage des UserControllers
 *
 * @author Benito Ernst
 * @version  01/2024
 */
public class UserResponse {

    private int id;

    private String username;

    private int level;

    /**
     * Setter für die User-Id
     *
     * @param userId Id des Users
     * @return Gibt die aktuelle Instanz der Klasse zurück
     */
    public UserResponse setId(int userId) {
        this.id = userId;
        return this;
    }

    /**
     * Setter für den Username
     *
     * @param username Name des Users
     * @return Gibt die aktuelle Instanz der Klasse zurück
     */
    public UserResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * Setter für das User-Level
     *
     * @param level Userlevel
     * @return Gibt die aktuelle Instanz der Klasse zurück
     */
    public UserResponse setLevel(int level) {
        this.level = level;
        return this;
    }

    /**
     * Getter für das User-Id
     *
     * @return Gibt den Wert aus dem Attribut "id" zurück
     */
    public int getId() {
        return id;
    }

    /**
     * Getter für den Username
     *
     * @return Gibt den Wert aus dem Attribut "username" zurück
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter für das User-Level
     *
     * @return Gibt den Wert aus dem Attribut "level" zurück
     */
    public int getLevel() {
        return level;
    }
}
