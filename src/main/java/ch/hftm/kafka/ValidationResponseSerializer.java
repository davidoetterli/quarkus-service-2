package ch.hftm.kafka;

import ch.hftm.dto.ValidationResponse;
import io.quarkus.kafka.client.serialization.JsonbSerializer;

public class ValidationResponseSerializer extends JsonbSerializer<ValidationResponse> {
    public ValidationResponseSerializer() {
        super();
    }
}
