package de.dlsa.api.responses;

import java.util.Date;

/**
 * Klasse zur Darstellung eines History-Eintrags bei Rückgabe aus der Get-Anfrage des UserControllers
 *
 * @author Benito Ernst
 * @version  01/2024
 */
public class HistoryItemResponse {
    private Integer level;
    private Integer score;
    private Date date;

    /**
     * Setter für das User-Level
     *
     * @param level Level
     * @return Gibt die aktuelle Instanz der Klasse zurück
     */
    public HistoryItemResponse setLevel(Integer level) {
        this.level = level;
        return this;
    }

    /**
     * Setter für das Spieldatum
     *
     * @param date Spieldatum
     * @return Gibt die aktuelle Instanz der Klasse zurück
     */
    public HistoryItemResponse setDate(Date date) {
        this.date = date;
        return this;
    }

    /**
     * Setter für den Score
     *
     * @param score Erreichter Score
     * @return Gibt die aktuelle Instanz der Klasse zurück
     */
    public HistoryItemResponse setScore(Integer score) {
        this.score = score;
        return this;
    }

    /**
     * Getter für das User-Level
     *
     * @return Gibt den Wert aus dem Attribut "level" zurück
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Getter für das Spieldatum
     *
     * @return Gibt den Wert aus dem Attribut "date" zurück
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter für den Score
     *
     * @return Gibt den Wert aus dem Attribut "score" zurück
     */
    public Integer getScore() {
        return score;
    }
}
