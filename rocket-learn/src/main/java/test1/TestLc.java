package test1;

import java.util.LinkedList;
import java.util.List;

/**
 * @author liuyang
 * @description
 * @date 2021/02/23 22:08
 **/
public class TestLc {

    static int ans = 0;
    static List<List<Integer>> result = new LinkedList<>();
    public static void main(String[] args) {
        int[] nums = {1, 1, 1,1,1};
        int a = findTargetSumWays(nums, 3);
        System.out.println(a);
    }

    public static int findTargetSumWays(int[] nums, int S) {
        backTrace(nums, 0, 0, S);
        return ans;
    }

    static void backTrace(int[] nums, int index,  int sum, int target) {
        if (index == nums.length) {
            if (sum == target) {
                ans++;
//                result.add(trace);
            }
            return;
        }
//        for (int i = index; i < nums.length; i++) {
            int[] select = new int[2];
            select[0] = -nums[index];
            select[1] = nums[index];
            for (int j = 0; j < 2; j++) {
                sum += select[j];
//                trace.add(select[j]);
                backTrace(nums, index + 1, sum, target);
//                trace.removeLast();
                sum -= select[j];
            }
//
//        }
//        backTrace(nums, index+1,sum+nums[index],target);
//        backTrace(nums, index+1,sum-nums[index],target);


    }





}
