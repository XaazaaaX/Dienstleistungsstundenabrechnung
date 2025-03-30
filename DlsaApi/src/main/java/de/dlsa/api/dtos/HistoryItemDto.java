package de.dlsa.api.dtos;

import java.util.Date;

/**
 * Klasse zur Übertragung eines HistoryItems vom Client zum Server
 *
 * @author Benito Ernst
 * @version  01/2024
 */
public class HistoryItemDto {

    public Integer level;
    public Date date;
    public Integer score;

    /**
     * Getter für das Level
     *
     * @return Gibt den Wert aus dem Attribut "level" zurück
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Getter für das Datum und die Uhrzeit
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
