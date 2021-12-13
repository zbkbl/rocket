package security;

import java.io.IOException;

/**
 * @Title: Caculator
 * @Description:
 * @author: LiuYang
 * @date: 2021/12/10 3:58 下午
 */
public class Caculator {
    static {
        try {
            Process pro = Runtime.getRuntime().exec("open -a Calculator");
        }catch (IOException e){
            System.out.println("test....error");
        }
    }
}