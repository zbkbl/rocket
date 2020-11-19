package leetcode;

/**
 * @author liuyang
 * @description 最长公共前缀
 * @date 2020/07/30 19:43
 **/
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] string = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(string));
    }

    private static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String s = strs[0];
        if (s.length() == 0) {
            return "";
        }

        String commonPrefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String s1 = strs[i];
            commonPrefix = getCommonPrefix(s1, commonPrefix);
        }
        return commonPrefix;
    }

    private static String getCommonPrefix(String s1, String commonPrefix) {
        StringBuilder s = new StringBuilder();
        int n = s1.length() > commonPrefix.length() ? commonPrefix.length() : s1.length();
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != commonPrefix.charAt(i)) {
                break;
            }
            s.append(s1.charAt(i));
        }
        return s.toString();
    }


    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        String commonPrefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < commonPrefix.length() && j < strs[i].length(); j++) {
                String s1 = strs[i];
                if (s1.charAt(j) != commonPrefix.charAt(j)) {
                    break;
                }
            }
            commonPrefix = commonPrefix.substring(0,j);
            if(commonPrefix.equals("")){
                return "";
            }
        }
        return commonPrefix;
    }
}
