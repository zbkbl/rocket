package flinkDemo;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-08-05 17:16
 **/
public class ReadFromKafka {

    public static void main(String[] args) throws Exception {


        // 获取运行环境
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //默认情况下，检查点被禁用。要启用检查点，请在StreamExecutionEnvironment上调用enableCheckpointing(n)方法，
        // 其中n是以毫秒为单位的检查点间隔。每隔5000 ms进行启动一个检查点,则下一个检查点将在上一个检查点完成后5秒钟内启动
//        env.enableCheckpointing(5000);
//        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
//        env.setParallelism(2);

        Map<String, String> properties = new HashMap<>();
        properties.put("bootstrap.servers", "v-commonkafka-01:9092,v-commonkafka-02:9092,v-commonkafka-03:9092");
        properties.put("group.id", "pixiu-flink");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("topic", "bg_action_3");

        ParameterTool parameterTool = ParameterTool.fromMap(properties);

        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(parameterTool.getRequired("topic"),
                new SimpleStringSchema(), parameterTool.getProperties());

        // Source: 数据源，Flink 在流处理和批处理上的 source 大概有 4 类：基于本地集合的 source、基于文件的 source、基于网络套接字的 source、自定义的 source。
        // 自定义的 source 常见的有 Apache kafka、Amazon Kinesis Streams、RabbitMQ、Twitter Streaming API、Apache NiFi 等，当然你也可以定义自己的 source
        DataStream<String> messageStream = env.addSource(consumer).setParallelism(1);

        // Transformation：数据转换的各种操作，有 Map / FlatMap / Filter / KeyBy / Reduce / Fold / Aggregations / Window / WindowAll
        // / Union / Window join / Split / Select / Project 等，操作很多，可以将数据转换计算成你想要的数据。
        messageStream.rebalance().map(new MapFunction<String, String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String map(String s) throws Exception {
                return s;
            }
        //  Sink：接收器，Flink 将转换计算后的数据发送的地点 ，你可能需要存储下来，Flink 常见的 Sink 大概有如下几类：写入文件、打印出来、写入 socket 、
        //  自定义的 sink 。自定义的 sink 常见的有 Apache kafka、RabbitMQ、MySQL、ElasticSearch、Apache Cassandra、Hadoop FileSystem 等，同理你也可以定义自己的 sink
        });
//        .assignTimestampsAndWatermarks().addSink(new SinkFunction<String>() {
//            @Override
//            public void invoke(String value, Context context) throws Exception {
//
//            }
//        });
        messageStream.print();
        env.execute("Flink Streaming Java API Skeleton");

    }

}
