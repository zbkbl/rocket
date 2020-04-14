package leetcode;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-11-16 10:53
 **/
public class Atoi {
    public static void main(String[] args) {
        System.out.println(atoi("2147483646"));
    }

    private static int string2Int(String str) {
        if (str == null) return 0;
        str = str.trim();
        if (str.equals("")) return 0;
        char c = str.charAt(0);
        if ((c > 57 || c < 48) && c != '-' && c != '+') {
            return 0;
        }

        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 && (chars[i] == '-' || chars[i] == '+' || (47 < chars[i] && chars[i] < 58))) {
                sb.append(chars[i]);
                continue;
            }
            if (47 < chars[i] && chars[i] < 58) {
                sb.append(chars[i]);
            } else {
                break;
            }
        }
        String st = sb.toString();
        int res = 0;
        try {
            res = Integer.parseInt(st);
        } catch (NumberFormatException e) {
            if (st.startsWith("-")) return st.length() > 1 ? Integer.MIN_VALUE : 0;
            if (st.startsWith("+")) return st.length() > 1 ? Integer.MAX_VALUE : 0;
            return Integer.MAX_VALUE;
        }
        return res;
    }

    private static int atoi(String str) {
        if (str == null) return 0;
        str = str.trim();
        if (str.equals("")) return 0;


        int i = 0;
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }
        if (i == str.length())
            return 0;
        char[] chars = str.toCharArray();
        int flag = 1;
        int res = 0;
        char c = chars[i];
        if (c == '-') {
            flag = -1;
            i++;
        }
        if (c == '+') {
            flag = 1;
            i++;
        }
        if (47 < c && c < 58) {
            flag = 1;
        }
        for (; i < chars.length; i++) {
            if (47 > chars[i] || chars[i] > 58) break;
            res = res * 10 + (chars[i] - '0');
            boolean condition = i + 1 < chars.length && chars[i + 1] > 47 && chars[i + 1] < 58;
            if (flag > 0 && condition && res > Integer.MAX_VALUE / 10) {
                return Integer.MAX_VALUE;
            }
            if (flag > 0 && condition && res == Integer.MAX_VALUE / 10 && chars[i + 1] - '0' > Integer.MAX_VALUE % 10)
                return Integer.MAX_VALUE;
            if (flag < 0 && condition && -res < Integer.MIN_VALUE / 10)
                return Integer.MIN_VALUE;
            if (flag < 0 && condition && -res == Integer.MIN_VALUE / 10 && -(chars[i + 1] - '0') < Integer.MIN_VALUE % 10)
                return Integer.MIN_VALUE;

        }
        return flag * res;
    }
}
