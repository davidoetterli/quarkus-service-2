package ch.hftm.kafka;

import ch.hftm.dto.ValidationRequest;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

/**
 * Deserializer für ValidationRequest Objekte.
 */
public class ValidationRequestDeserializer extends JsonbDeserializer<ValidationRequest> {
    /**
     * Konstruktor.
     */
    public ValidationRequestDeserializer() {
        super(ValidationRequest.class);
    }
}
