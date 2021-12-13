package com.zbkbl.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zbkbl.demo.aviator.PhfEditionProduct;
import com.zbkbl.demo.vo.MonitorChange;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.googlecode.aviator.AviatorEvaluator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

//@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    public void testjson(){
        String s = "[{\"database\":\"xxxx\",\"table\":\"xxx\",\"fields\":[\"111\",\"222\"]},{\"database\":\"xxxx\",\"table\":\"xxx\",\"fields\":[\"333\",\"444\"]}]";
        List<MonitorChange> jsonArray = JSONArray.parseArray(s, MonitorChange.class);
        System.out.println(jsonArray);

    }

    @Test
    public void testPattern(){
        String s = "wmspecialsku.wm_food_105";
        String pattern = "wmspecialsku.wm_food_\\d+";
        boolean isMatch = Pattern.matches(pattern, s);
        System.out.println(isMatch);

    }

    @Test
    public void testJson(){
        String diffMapMsgBody = "{\"tag_name\":\"\",\"tag_id\":\"\"}";
        JSONObject diffMap = JSON.parseObject(diffMapMsgBody);
        List<String> test = new ArrayList<>();
        test.add("tag_id");
        test.add("price");
        test.retainAll(diffMap.keySet());
        System.out.println(test);
        Set<String> str = new HashSet<>();
        str.add("tag_name");
        System.out.println(str.iterator().next());
    }

    @Test
    public void testAviator(){
        PhfEditionProduct phfEditionProduct = new PhfEditionProduct();
        phfEditionProduct.setOriPrice(10);
        phfEditionProduct.setSingleShippingAmount(20);
        phfEditionProduct.setSingleShippingSubsidy(3);
        Map<String, Object> env = new HashMap<>();
        env.put("p", phfEditionProduct);
        System.out.println(AviatorEvaluator.execute("p.singleShippingAmount - p.singleShippingSubsidy", env));
    }
}
