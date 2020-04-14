package demo;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liuyang
 * @description test
 * @date 2020/01/14 11:17
 **/

public class test {

    public static void main(String[] args) {

        String bossId = String.valueOf(34226972);
        // 获取倒数第二个尾号
        boolean flag = false;
        // 获取倒数第二个尾号
        if(bossId.length() > 2) {
            int tailNumber = Integer.parseInt(bossId.substring(bossId.length() - 2, bossId.length() - 1));
            flag = tailNumber >= 5 && tailNumber <= 9;
            System.out.println(tailNumber);
        }
    }
}
