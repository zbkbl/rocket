package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuyang
 * @description
 * @date 2020/07/30 20:28
 **/
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if (len < 3)
            return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break; // 第一个数 > 0,sum一定 > 0
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int L = i + 1; // 左指针
            int R = len - 1; //右指针
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) L++;
                    while (L < R && nums[R] == nums[R - 1]) R--;
                    L++;
                    R--;
                }
                if (sum < 0)
                    L++;
                if (sum > 0)
                    R--;
            }

        }

        return result;
    }
}
