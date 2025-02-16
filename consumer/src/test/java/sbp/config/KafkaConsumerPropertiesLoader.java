package sbp.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class KafkaConsumerPropertiesLoader {

    public static Properties getKafkaConsumerProperties(String groupId) {
        Properties appProps = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/kafka-consumer.properties")) {
            appProps.load(input);
        } catch (IOException e) {
            log.error("Ошибка при загрузке свойств consumer: {}", e.getMessage());
        }

        appProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return appProps;
    }
}
