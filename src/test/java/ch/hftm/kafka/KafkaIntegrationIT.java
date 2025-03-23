/**
 * Enthält Integrationstests, Serialisierungs‑/Deserialisierungs‑Tests und
 * Testressourcen für Kafka.
 */
package ch.hftm.kafka;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * Integrationstest für Kafka.
 */
@QuarkusIntegrationTest
@QuarkusTestResource(KafkaTestResource.class)
public class KafkaIntegrationIT {


    /**
     * Testet die Kafka-Integration.
     */
    @Test
    public void testKafkaIntegration() {
        String bootstrapServers = KafkaTestResource.bootstrapServers;

        // Producer konfigurieren
        Properties producerProps = new Properties();
        producerProps.put("bootstrap.servers", bootstrapServers);
        producerProps.put("key.serializer", StringSerializer.class.getName());
        producerProps.put("value.serializer", StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<>(producerProps);

        // Sende eine JSON-Nachricht, die einen gültigen Validierungsfall repräsentiert
        String requestJson = "{\"identifier\":100, \"text\":\"Das ist ein Test ohne verbotene Inhalte\"}";
        ProducerRecord<String, String> record = new ProducerRecord<>("validation-request", requestJson);
        producer.send(record);
        producer.close();

        // Consumer konfigurieren
        Properties consumerProps = new Properties();
        consumerProps.put("bootstrap.servers", bootstrapServers);
        consumerProps.put("group.id", "test-group");
        consumerProps.put("auto.offset.reset", "earliest");
        consumerProps.put("key.deserializer", StringDeserializer.class.getName());
        consumerProps.put("value.deserializer", StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Collections.singleton("validation-response"));

        // Warte, bis die Nachricht empfangen wird (Timeout: 10 Sekunden)
        boolean messageReceived = false;
        long timeout = System.currentTimeMillis() + 10000;
        while (System.currentTimeMillis() < timeout && !messageReceived) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
            for (ConsumerRecord<String, String> rec : records) {
                if (rec.value().contains("\"identifier\":100") && rec.value().contains("\"valid\":true")) {
                    messageReceived = true;
                    break;
                }
            }
        }
        consumer.close();
        Assertions.assertTrue(messageReceived, "Es wurde keine gültige Antwort im Topic 'validation-response' empfangen.");
    }
}
