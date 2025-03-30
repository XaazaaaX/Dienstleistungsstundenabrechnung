package de.dlsa.api.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Model-Klasse von User für die DB
 *
 * @author Benito Ernst
 * @version  01/2024
 */
@Table(name = "Benutzer")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "id")
    private Integer user_id;

    @Column(nullable = false, name = "benutzername")
    private String username;

    @Column(nullable = false, name = "passwort")
    private String password;

    @Column(nullable = false, name = "aktiv")
    private boolean active;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Role role;

    /**
     * Getter für das User-Id
     *
     * @return Gibt den Wert aus dem Attribut "id" zurück
     */
    public Integer getId() {
        return user_id;
    }

    /**
     * Getter für den Username
     *
     * @return Gibt den Wert aus dem Attribut "username" zurück
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Setter für den Username
     *
     * @param username Name des Users
     * @return Gibt die aktuelle Instanz der Klasse zurück
     */
    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * Keine Verwendung
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Keine Verwendung
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Keine Verwendung
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Keine Verwendung
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Keine Verwendung
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * Getter für das User-Passwort
     *
     * @return Gibt den Wert aus dem Attribut "password" zurück
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter für das User-Level
     *
     * @param password Passwort
     * @return Gibt die aktuelle Instanz der Klasse zurück
     */
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public User setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }
}
