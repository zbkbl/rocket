package demo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.*;

/**
 * @description: Redis使用工具类
 * @author: Zhangmi
 * @create: 2018-10-31 17:38
 **/
public class RedisUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class);


    private Set<String> redisKey = Sets.newConcurrentHashSet();


    public RedisUtils(String address) {
    }
}
