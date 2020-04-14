package demo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.net.URI;
import java.util.*;

/**
 * @description: Redis使用工具类
 * @author: Zhangmi
 * @create: 2018-10-31 17:38
 **/
public class RedisUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class);

    private JedisPool pool = null;

    private Set<String> redisKey = Sets.newConcurrentHashSet();


    public RedisUtils(String address) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(50);
        config.setMaxIdle(10);
        config.setMaxWaitMillis(500);
        // 获得一个jedis实例的时候是否检查连接可用性（ping()）
        config.setTestOnBorrow(true);
        // return 一个jedis实例给pool时，是否检查连接可用性（ping()）
        config.setTestOnReturn(true);
        pool = new JedisPool(config, URI.create("redis://" + address), 6000, 6000);
        warm();
        LOGGER.info("new RedisUtils success");
    }


    private void warm() {
        try (Jedis resource = pool.getResource()) {
            resource.ping();
        } catch (Exception e) {
            LOGGER.error("warm error", e);
        }
    }

    public void set(String key, String value) {
        try (Jedis resource = pool.getResource()) {
            resource.set(key, value);
            redisKey.add(key);
        } catch (Exception e) {
            LOGGER.error("redis Key:{}, value:{}, set error, exception={}", key, value, e.getMessage());
        }
    }

    /**
     * redis存放数据时，一定要指定过期时间，防止redis存放垃圾数据
     *
     * @return void
     * @params [key, value, seconds]
     * @date 2018/11/1
     */
    public void set(String key, String value, int seconds) {
        try (Jedis resource = pool.getResource()) {
            resource.set(key, value);
            resource.expire(key, seconds);
            redisKey.add(key);
            LOGGER.info("redis set: key={}", key);
        } catch (Exception e) {
            LOGGER.error("redis Key:{}, value:{}, set error, exception={}", key, value, e.getMessage());
        }
    }

    public String get(String key) {
        try (Jedis resource = pool.getResource()) {
            return resource.get(key);
        } catch (Exception e) {
            LOGGER.error("redis Key:{}, get error, exception={}", key, e.getMessage());
        }
        return null;
    }

    public JSONObject getRealTimeReport(String key) {
        try (Jedis resource = pool.getResource()) {
            return JSONObject.parseObject(resource.get(key));
        } catch (Exception e) {
            LOGGER.error("redis Key:{}, get error, exception={}", key, e.getMessage());
        }
        return null;
    }

    public Long delete(String key) {
        try (Jedis resource = pool.getResource()) {
            redisKey.remove(key);
            return resource.del(key);
        } catch (Exception e) {
            LOGGER.error("redis Key:{}, delete error, exception={}", key, e.getMessage());
        }
        return 0L;
    }

    public boolean exists(String key) {
        try (Jedis resource = pool.getResource()) {
            return resource.exists(key);
        } catch (Exception e) {
            LOGGER.error("redis Key:{}, exists error, exception={}", key, e.getMessage());
        }
        return false;
    }

    public Long expire(String key, int seconds) {
        try (Jedis resource = pool.getResource()) {
            return resource.expire(key, seconds);
        } catch (Exception e) {
            LOGGER.error("redis Key:{}, seconds:{}, expire error, exception={}", key, seconds, e.getMessage());
        }
        return 0L;
    }

    public void removeAll() {
        LOGGER.error("current redis size:{}", redisKey.size());

        if (CollectionUtils.isEmpty(redisKey)) {
            LOGGER.error("current redis size is empty, remove all keys finish");
            return;
        }
        try (Jedis resource = pool.getResource()) {
            redisKey.forEach(key -> resource.del(key));
        } catch (Exception e) {
            LOGGER.error("redis Key:{}, expire error, exception={}", redisKey, e.getMessage());
        }
        LOGGER.error("remove all keys finish ~~~");
    }


    public void hset(String key, String field, String value) {
        try (Jedis jedis = pool.getResource()) {
            jedis.hset(key, field, value);
        } catch (Exception e) {
            LOGGER.error("key:{} value:{} error", key, value, e);
        }
    }

    public String hget(String key, String field, String defaultValue) {
        try (Jedis resource = pool.getResource()) {
            String value = resource.hget(key, field);
            return value;
        } catch (Exception e) {
            LOGGER.error("key:{},field:{} hget error", key, field, e);
        }
        return defaultValue;
    }

    public static String buildKey(String position, String simPosition) {
        // PREFIX中加入缓存版本号，如果存入缓存的数据结构发生变化，则需手工更新版本号，使历史数据失效
        return position + "-" + simPosition;
    }

}
