package test1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuyang
 * @description
 * @date 2020/11/07 11:09
 **/
public class Container {

    private Map<String, String> map = new ConcurrentHashMap<>();


    public  void setKV(String key, String  value) {
        map.put(key, value);
    }

    public  String getValue(String key){
        return map.get(key);
    }
}
