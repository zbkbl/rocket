package leetcode;

import java.util.Stack;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-11-14 18:53
 **/
public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println(reverse2(-123));
    }

    private static int reverse(int x) {
        String s = String.valueOf(x);
        StringBuilder a = new StringBuilder();
        boolean flag = s.startsWith("-");
        if (flag) {
            a.append(s.substring(1));
        } else {
            a.append(s);
        }
        String result = a.reverse().toString();
        while (result.startsWith("0")) {
            result = result.substring(1);
        }
        int r = 0;
        if (flag) {
            try {
                r = Integer.parseInt("-" + result);
            } catch (NumberFormatException e) {
                return 0;
            }

        } else {
            try {
                r = Integer.parseInt(result);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return r;
    }

    private static int reverse2(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if(rev <Integer.MIN_VALUE /10 || (rev == Integer.MIN_VALUE/10 && pop<-8)) return 0;
            rev = rev * 10 + pop;
        }

        return rev;
    }
}
