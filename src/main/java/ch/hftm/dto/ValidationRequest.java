package ch.hftm.dto;

/**
 * Repräsentiert eine Validierungsanfrage.
 *
 * @param id die eindeutige ID
 * @param text der zu validierende Text
 */

public record ValidationRequest(long id, String text) {
}
