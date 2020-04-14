package flinkDemo;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-08-07 15:55
 **/
public class ReadFromMysql {

    public static void main(String[] args) throws  Exception{
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.addSource(new SourceFromMySQL()).print();

        env.execute("Flink add data source");
    }
}
