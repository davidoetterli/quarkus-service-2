/**
 * Enthält die Data Transfer Objects (DTOs) für die Validierung.
 */
package ch.hftm.dto;

/**
 * Repräsentiert die Antwort einer Validierungsanfrage.
 *
 * @param identifier die eindeutige ID
 * @param valid      das Ergebnis der Validierung
 */
public record ValidationResponse(long identifier, boolean valid) {
}
