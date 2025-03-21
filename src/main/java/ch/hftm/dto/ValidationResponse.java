package ch.hftm.dto;

/**
 * Repräsentiert die Antwort einer Validierungsanfrage.
 *
 * @param id die eindeutige ID
 * @param valid das Ergebnis der Validierung
 */

public record ValidationResponse(long id, boolean valid) {
}
