package de.dlsa.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Einstiegspunkt der Anwendung
 *
 * @author Benito Ernst
 * @version  01/2024
 */
@SpringBootApplication
public class DlsaApiApplication {

    /**
     * Methode zum Start der API
     *
     * @param args Angabe von Startparametern
     */
    public static void main(String[] args) {
        SpringApplication.run(DlsaApiApplication.class, args);
    }

}
