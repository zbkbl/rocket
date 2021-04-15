package test1;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-07-19 16:57
 **/

public class Test {
    public static void main(String[] args) {

        Class<Demo> clazz = Demo.class;
        try {
            Field field = clazz.getField("name");
            Seven sevenField = field.getAnnotation(Seven.class);
            System.out.println(sevenField.value());
            System.out.println(sevenField.Property());

            Method method =clazz.getMethod("hello");
            Seven sevenMethod = method.getAnnotation(Seven.class);
            System.out.println(sevenMethod.value());
            System.out.println(sevenMethod.Property());

            Field field1 = clazz.getField("age");
            Seven ageFiled = field1.getAnnotation(Seven.class);
            System.out.println(ageFiled.value());
            System.out.println(ageFiled.Property());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


//    if (index == nums.length) {
//        if (sum == target) {
//            ans++;
//            result.add(cur);
//        }
//        return;
//    }
//        for (int i = index; i < nums.length; i++) {
//        int[] select = new int[2];
//        select[0] = -nums[i];
//        select[1] = nums[i];
//        for (int j = 0; j < 2; j++) {
//            sum += select[j];
//            cur.add(select[j]);
//            backTrace(nums, i + 1, target, sum, cur);
//            cur.removeLast();
//            sum -= select[j];
//        }
//    }


    private static boolean recur(char[] ss,char[] pp, int sidx , int pidx){
        //结束条件确实恶心,各种idxOutBound
        if (pidx < 0) {
            return sidx == pidx;
        }
        if (sidx < 0) {
            while (pidx >= 0 && pp[pidx] == '*') {//bug_注意:pidx>=0
                pidx -= 2;
            }
            return pidx == sidx;
        }

        //下面逻辑代码不需要考虑idx为负数,就算idx负数,那也是下一个recur去判断.
        if (pp[pidx] != '*') {//不是*好说,要么点,要么字母
            if (pp[pidx] == '.'|| pp[pidx] == ss[sidx]) {//是点,点可以匹配任意,所以直接recur
                return recur(ss, pp, sidx - 1, pidx - 1);
            } else {
                return false;//s和p的末尾字母,不相同直接gg,返false
            }
        } else {
            //根据题意:*匹配0个or多个
            if (recur(ss, pp, sidx, pidx - 2)) return true;//这是:*匹配0个

            char ctemp = pp[pidx - 1];//下面是*匹配多个
            int sidxmove = sidx;
            /**
             * 用p的末尾去匹配s的末尾的1个,2个,3个,4个...不可能匹配掉无限个吧!!用*把ss[0]都匹配掉了应该是极限了
             */
            while (sidxmove >= 0 && (ctemp == '.' || ctemp == ss[sidxmove])) {//用*匹配掉1个,2个,3个,4个...
                if (recur(ss, pp, sidxmove - 1, pidx - 2)) {
                    return true;//加速加速
                }
                sidxmove--;
            }
            return false;//上面加速都不成功,说明没救了
        }
    }



}
