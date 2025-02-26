package ch.hftm.kafka;

import ch.hftm.dto.ValidationRequest;
import io.quarkus.kafka.client.serialization.JsonbSerializer;

public class ValidationRequestSerializer extends JsonbSerializer<ValidationRequest> {
    public ValidationRequestSerializer() {
        super();
    }
}
