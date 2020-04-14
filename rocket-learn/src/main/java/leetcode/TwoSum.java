package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: Two Sum
 * @author: Liuyang
 * @date: 2019-10-14 14:39
 **/
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 26;
        System.out.println(Arrays.toString(otherSolution(nums, target)));
    }

    /**
     * 时间复杂度O(n^2),空间复杂度O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    /**
     * 时间复杂度O(n),空间复杂度O(n)
     * @param nums
     * @param target
     * @return
     */
    private static int[] otherSolution(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (map.containsKey(target - x)) {
                result[0] = map.get(target - x);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }


        return result;
    }



}
