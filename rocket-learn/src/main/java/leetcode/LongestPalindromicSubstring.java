package leetcode;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-10-18 15:23
 **/
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String s = "aa";
        System.out.println(longestPalindrome(s));
        System.out.println(longestPalindrome1(s));
        System.out.println(longestPalindrome2(s));
    }

    //以每个字符或两个字符为中心的回文子串， 中心扩展
    private static String longestPalindrome(String s) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2 + 1;
            }
        }
        return s.substring(start, end);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left;
        int R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    // 动态规划
    private static String longestPalindrome1(String s) {
        if (s.isEmpty()) {
            return s;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int left = 0;
        int right = 0;
        // dp[i][j] == s.charAt[i]==s.charAt[j] && dp[i+1][j-1]
        for (int i = n - 2; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (dp[i + 1][j - 1] || j - i < 3);
                if (dp[i][j] && right - left < j - i) {
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }


    private static String longestPalindrome2(String s) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandCenter(s, i, i);
            int len2 = expandCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandCenter(String s, int left, int rigth) {
        int L = left;
        int R = rigth;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

}
