package demo;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liuyang
 * @description test
 * @date 2020/01/14 11:17
 **/

public class test {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,11);
        map.put(2,22);
        map.put(3,33);
        map.put(4,44);
        map.put(5,55);
        map.put(6,66);
        map.put(7,77);
        map.put(8,88);
        map.put(9,99);
        List<Integer> comIdList = Arrays.asList(1,2,3,4,5,6,7,8,9,2,4,6,9,6,4,2);
        try {
            int per = 0;
            int batch= 3;
            int size = comIdList.size();
            List<Integer> result = new ArrayList<>();
            for (; (per + 1) * batch < size; per++) {
                List<Integer> subList = comIdList.subList(batch * per, batch * (per + 1));
//                List<MaskCompany> currentList = bossDBApi.getUserMaskCompany(subList);
                List<Integer> currentList = subList.stream().map(i -> map.get(i)).collect(Collectors.toList());
                result.addAll(currentList);
            }
            List<Integer> lastList = comIdList.subList(per * batch, size);
//            List<MaskCompany> lastCompanyList = bossDBApi.getUserMaskCompany(lastList);
            List<Integer> lastCompanyList = lastList.stream().map(i -> map.get(i)).collect(Collectors.toList());
            result.addAll(lastCompanyList);
        } catch (Exception e) {
        }
    }
}
