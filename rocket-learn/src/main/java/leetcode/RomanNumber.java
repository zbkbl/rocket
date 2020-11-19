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
        System.out.println(romanToInt("MCMXCIV"));
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
        StringBuilder sb = new StringBuilder();
        int[] number = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] arab = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int index = 0;
        while (num > 0) {
            int count = num / number[index];
            while (count-- > 0) {
                sb.append(arab[index]);
            }
            num %= number[index];
            index++;
        }
        return sb.toString();
    }

    private static int romanToInt(String s) {
        int[] number = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] arab = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int res = 0;
        int i = 0;
        int index = -1;
        while (i < s.length()) {
            int current;
            String target = s.substring(i, i + 1);
            if (i + 1 < s.length()) {
                String sub = s.substring(i, i + 2);
                if (sub.equals("CM") || sub.equals("CD")
                        || sub.equals("XC") || sub.equals("XL")
                        || sub.equals("IX") || sub.equals("IV")) {
                    target = sub;
                    i += 2;
                } else {
                    i += 1;
                }
            } else {
                i += 1;
            }
            int j = 0;
            while (j < arab.length) {
                if (arab[j].equals(target)) {
                    index = j;
                    break;
                }
                j++;
            }
            res += number[index];
        }
        return res;
    }

//    private static Map<String, Integer> getMap() {
//        int[] number = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
//        String[] arab = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
//        Map<String, Integer> map = new HashMap<>(13);
//        for (int i = 0; i < number.length; i++) {
//            map.put(arab[i], number[i]);
//        }
//        return map;
//    }
}
