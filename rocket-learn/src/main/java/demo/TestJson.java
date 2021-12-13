package demo;


import com.alibaba.fastjson.JSONArray;

import java.util.Iterator;

/**
 * @Title: TestJSOn
 * @Description:
 * @author: LiuYang
 * @date: 2021/6/7 8:21 下午
 */
public class TestJson {
    public static void main(String[] args) {
        String s = "[{\"database\":\"xxxx\",\"table\":\"xxx\",\"fields\":[\"xxx\",\"xxx\"]},{\"database\":\"xxxx\",\"table\":\"xxx\",\"fields\":[\"xxx\",\"xxx\"]}]";
        JSONArray jsonArray = JSONArray.parseArray(s);
        Iterator<Object> iterator = jsonArray.stream().iterator();
        while (iterator.hasNext()){
            Object o = iterator.next();
            System.out.println(o);
        }
    }
}