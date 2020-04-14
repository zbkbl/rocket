package demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * @description: 刷数据程序
 * @author: Liuyang
 * @date: 2020-01-13 10:12
 **/
public class FlushDataMain {

    public static void main(String[] args) {

        // 初始化redis工具
        RedisUtils redisUtils = new RedisUtils("127.0.0.1:6379");
        try {
            // 读文件
            BufferedReader br = new BufferedReader(new InputStreamReader(FlushDataMain.class.getClassLoader().getResourceAsStream("pos_sim_score.txt")));
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t");
                BigDecimal bigDecimal = new BigDecimal(split[2]);
                String key = RedisUtils.buildKey(split[0], split[1]);
                // 往redis里写数据
                redisUtils.hset("fileName", key, bigDecimal.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String value = redisUtils.hget("fileName",100115 + "-" + 100104,"defaultValue");
        System.out.println(value);
    }
}
