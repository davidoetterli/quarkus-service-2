/**
 * Enthält Integrationstests, Serialisierungs‑/Deserialisierungs‑Tests und
 * Testressourcen für Kafka.
 */
package ch.hftm.kafka;

import ch.hftm.dto.ValidationRequest;
import ch.hftm.dto.ValidationResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testet die Serialisierung und Deserialisierung.
 */
public class KafkaSerializationTest {

    /**
     * Testet die Serialisierung von ValidationResponse.
     *
     * @throws Exception falls ein Fehler auftritt
     */
    @Test
    public void testValidationResponseSerialization() throws Exception {
        ValidationResponse response = new ValidationResponse(123L, true);
        ValidationResponseSerializer serializer = new ValidationResponseSerializer();
        byte[] serialized = serializer.serialize(null, response);
        String jsonString = new String(serialized);
        // Überprüfung, ob das JSON die erwarteten Felder enthält
        assertTrue(jsonString.contains("\"id\":123"));
        assertTrue(jsonString.contains("\"valid\":true"));
    }

    /**
     * Testet die Deserialisierung von ValidationRequest.
     *
     * @throws Exception falls ein Fehler auftritt
     */
    @Test
    public void testValidationRequestDeserialization() throws Exception {
        String jsonString = "{\"id\":456,\"text\":\"Testmessage\"}";
        byte[] bytes = jsonString.getBytes();
        ValidationRequestDeserializer deserializer = new ValidationRequestDeserializer();
        ValidationRequest request = deserializer.deserialize(null, bytes);
        assertEquals(456L, request.id());
        assertEquals("Testmessage", request.text());
    }
}
