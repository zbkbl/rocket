package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-07-16 14:15
 **/
public class LengthOfLongesSubStr {

    public static void main(String[] args) {
        String s = " ";
        int leng = lengthOfLongestSubstring(s);
        System.out.println(leng);
    }

    public static int lengthOfLongestSubstring(String s) {
        int l = s.length();
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < l; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            result = Math.max(result, j - i + 1);
            map.put(s.charAt(j), j + 1);

        }
        return result;
    }
}
