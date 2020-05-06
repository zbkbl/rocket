package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyang
 * @description
 * @date 2020/04/20 11:33
 **/
public class TestMap {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        map.put(1,list);
        map.put(2, new ArrayList<>());
        System.out.println(map);
        List<Integer> list2 = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
            e.getValue().add(99);
            list2.addAll(e.getValue());
        }
        System.out.println(map);
        System.out.println(list2);
    }
}
