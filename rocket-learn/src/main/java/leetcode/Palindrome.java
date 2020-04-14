package leetcode;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-11-21 17:01
 **/
public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome2(Integer.MAX_VALUE));
    }

    private static boolean isPalindrome(int x) {
        StringBuilder sb = new StringBuilder(x + "");
        String z = String.valueOf(x);
        String y = sb.reverse().toString();

        return z.equals(y);

    }

    private static boolean isPalindrome2(int x) {
        if (x < 0) return false;
        int sum = 0;
        int l = String.valueOf(x).length();
        int i = 1;
        int y = x;
        while (i <= l) {
            int u = y % 10;
            sum = sum * 10 + u;
            y /= 10;
            i++;
        }
        return sum == x;
    }

    private static boolean isPalindrome3(int x) {
        if (x < 0) return false;
        String s = String.valueOf(x);
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

    private static boolean isPalindrome4(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        int sum = 0;
        while (x > sum) {
            sum = sum * 10 + x % 10;
            x /= 10;
        }

        return x == sum || x == sum / 10;
    }
}
