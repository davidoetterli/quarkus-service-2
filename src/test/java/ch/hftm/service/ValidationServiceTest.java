/**
 * Enthält Tests für Services und Geschäftslogiken.
 */
package ch.hftm.service;

import ch.hftm.dto.ValidationRequest;
import ch.hftm.dto.ValidationResponse;
import io.smallrye.mutiny.Multi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testet den ValidationService.
 */
public class ValidationServiceTest {

    /**
     * Instanz des zu testenden Services.
     */
    private final ValidationService validationService = new ValidationService();

    /**
     * Testet, dass ein gültiger Text als valid erkannt wird.
     */
    @Test
    public void testValidText() {
        // Ein Text, der gut ist
        ValidationRequest request = new ValidationRequest(1L, "Das ist ein guter Test");
        Multi<ValidationResponse> responseMulti = validationService.validateTextMessages(Multi.createFrom().item(request));
        ValidationResponse response = responseMulti.toUni().await().indefinitely();
        assertTrue(response.valid(), "Erwartet: valid true");
    }

    /**
     * Testet, dass ein ungültiger Text als invalid erkannt wird.
     */
    @Test
    public void testInvalidText() {
        // Ein Text, der den verbotenen Ausdruck enthält
        ValidationRequest request = new ValidationRequest(2L, "HFTM sucks in allen Fällen");
        Multi<ValidationResponse> responseMulti = validationService.validateTextMessages(Multi.createFrom().item(request));
        ValidationResponse response = responseMulti.toUni().await().indefinitely();
        assertFalse(response.valid(), "Erwartet: valid false");
    }
}
