package ch.hftm.kafka;

import ch.hftm.dto.ValidationResponse;
import io.quarkus.kafka.client.serialization.JsonbSerializer;

/**
 * Serializer für ValidationResponse Objekte.
 */
public class ValidationResponseSerializer extends JsonbSerializer<ValidationResponse> {
    /**
     * Konstruktor.
     */
    public ValidationResponseSerializer() {
        super();
    }
}
