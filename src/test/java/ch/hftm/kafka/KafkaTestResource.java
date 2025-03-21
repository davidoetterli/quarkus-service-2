package ch.hftm.kafka;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;
import java.util.Map;

public class KafkaTestResource implements QuarkusTestResourceLifecycleManager {

    public static String bootstrapServers;  // Statische Variable fuer den Zugriff im Test
    private KafkaContainer kafkaContainer;

    @Override
    public Map<String, String> start() {
        kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.2.1"));
        kafkaContainer.start();
        bootstrapServers = kafkaContainer.getBootstrapServers();
        return Collections.singletonMap("kafka.bootstrap.servers", bootstrapServers);
    }

    @Override
    public void stop() {
        if (kafkaContainer != null) {
            kafkaContainer.stop();
        }
    }
}
