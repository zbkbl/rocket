package leetcode;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-11-22 15:50
 **/
public class RomanNumber {
    public static void main(String[] args) {
//        System.out.println(3*"I");
        System.out.println(intToRoman(1994));
    }

    private static String transToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int q = num / 1000;
        int b = (num % 1000) / 100;
        int s = (num % 100) / 10;
        int g = num % 10;
        if (q < 4) {
            for (int i = 0; i < q; i++) {
                sb.append("M");
            }
        }
        append(b, sb, "C", "D", "M");
        append(s, sb, "X", "L", "C");
        append(g, sb, "I", "V", "X");
        return sb.toString();
    }

    private static void append(int b, StringBuilder sb, String x, String y, String z) {
        if (b < 4) {
            for (int i = 0; i < b; i++) {
                sb.append(x);
            }
        } else if (b == 4) {
            sb.append(x + y);
        } else if (b < 9) {
            sb.append(y);
            for (int i = 0; i < b - 5; i++) {
                sb.append(x);
            }
        } else {
            sb.append(x + z);
        }
    }

    private static String intToRoman(int num) {
        StringBuilder ans = new StringBuilder();
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arab = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int index = 0;
        while (num > 0) {
            int count = num / arab[index];
            while (count-- > 0) {
                ans.append(roman[index]);
            }
            num %= arab[index];
            index++;
        }
        return ans.toString();
    }
}
