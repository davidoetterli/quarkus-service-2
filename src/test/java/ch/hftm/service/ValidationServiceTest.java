package ch.hftm.service;

import ch.hftm.dto.ValidationRequest;
import ch.hftm.dto.ValidationResponse;
import ch.hftm.service.ValidationService;
import io.smallrye.mutiny.Multi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationServiceTest {

    private final ValidationService validationService = new ValidationService();

    @Test
    public void testValidText() {
        // Ein Text, der gut ist
        ValidationRequest request = new ValidationRequest(1L, "Das ist ein guter Test");
        Multi<ValidationResponse> responseMulti = validationService.validateTextMessages(Multi.createFrom().item(request));
        ValidationResponse response = responseMulti.toUni().await().indefinitely();
        assertTrue(response.valid(), "Erwartet: valid true");
    }

    @Test
    public void testInvalidText() {
        // Ein Text, der den verbotenen Ausdruck enthält
        ValidationRequest request = new ValidationRequest(2L, "HFTM sucks in allen Fällen");
        Multi<ValidationResponse> responseMulti = validationService.validateTextMessages(Multi.createFrom().item(request));
        ValidationResponse response = responseMulti.toUni().await().indefinitely();
        assertFalse(response.valid(), "Erwartet: valid false");
    }
}
