package canal.producer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author liuyang
 * @description
 * @date 2020/05/07 17:29
 **/
public class KafkaProducer {

    static final String topic = "liuyang";

    static final String servers = "127.0.0.1:9092";


    public static void main(String[] args) {
        Producer<String, String> producer = buildKafkaProducer();
        ProducerRecord<String, String> pr = new ProducerRecord<>(topic, "{\"data\":[{\"id\":\"18\",\"name\":\"client\",\"password\":\"xxxx\",\"age\":\"1121\",\"update_time\":\"2020-05-09 11:13:33\"}],\"database\":\"rcd_pixiu\",\"es\":1588994016000,\"id\":10,\"isDdl\":false,\"mysqlType\":{\"id\":\"int(11) unsigned\",\"name\":\"varchar(25)\",\"password\":\"varchar(25)\",\"age\":\"int(10)\",\"update_time\":\"timestamp(0)\"},\"old\":[{\"update_time\":\"0000-00-00 00:00:00\"}],\"pkNames\":null,\"sql\":\"\",\"sqlType\":{\"id\":4,\"name\":12,\"password\":12,\"age\":4,\"update_time\":93},\"table\":\"student\",\"ts\":1588994016509,\"type\":\"UPDATE\"}");
        producer.send(pr, null);
        try {
            Thread.currentThread().join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static org.apache.kafka.clients.producer.KafkaProducer<String, String> buildKafkaProducer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", servers);
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return new org.apache.kafka.clients.producer.KafkaProducer<>(properties);
    }
}
