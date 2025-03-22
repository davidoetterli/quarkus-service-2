/**
 * Enthält Integrationstests, Serialisierungs‑/Deserialisierungs‑Tests und
 * Testressourcen für Kafka.
 */
package ch.hftm.kafka;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;
import java.util.Map;

/**
 * Testressource für Kafka mit Testcontainers.
 */
public class KafkaTestResource implements QuarkusTestResourceLifecycleManager {

    /**
     * Statische Variable für den Zugriff im Test.
     */
    public static String bootstrapServers;
    private KafkaContainer kafkaContainer;

    /**
     * Startet den Kafka-Container.
     *
     * @return Konfiguration als Map
     */
    @Override
    public Map<String, String> start() {
        kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.2.1"));
        kafkaContainer.start();
        bootstrapServers = kafkaContainer.getBootstrapServers();
        return Collections.singletonMap("kafka.bootstrap.servers", bootstrapServers);
    }

    /**
     * Stoppt den Kafka-Container.
     */
    @Override
    public void stop() {
        if (kafkaContainer != null) {
            kafkaContainer.stop();
        }
    }
}
