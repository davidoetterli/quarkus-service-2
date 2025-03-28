/**
 * Enthält die Data Transfer Objects (DTOs) für die Validierung.
 */
package ch.hftm.dto;

/**
 * Repräsentiert eine Validierungsanfrage.
 *
 * @param identifier die eindeutige ID
 * @param text       der zu validierende Text
 */
public record ValidationRequest(long identifier, String text) {
}
