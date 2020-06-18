package canal.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author liuyang
 * @description kafka client demo
 * @date 2020/05/06 15:13
 **/
public class kafkaConsumer {

    static final String topic = "item-limit";

    static final String servers = "127.0.0.1:9092";

    static final String group = "rocket";

    static final String KEY_DESERIALIZER = "org.apache.kafka.common.serialization.StringDeserializer";

    static final String VALUE_DESERIALIZER = KEY_DESERIALIZER;

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = buildKafkaConsumer();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                String s = record.value();
                System.out.println(s);
            }
        }
    }


    private static KafkaConsumer<String, String> buildKafkaConsumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", servers);
        properties.put("group.id", group);
        properties.put("key.deserializer", KEY_DESERIALIZER);
        properties.put("value.deserializer", VALUE_DESERIALIZER);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topic.split(",")));
        return consumer;
    }
}
