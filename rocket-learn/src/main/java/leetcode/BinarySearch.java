package leetcode;

/**
 * @author liuyang
 * @description
 * @date 2021/03/16 00:36
 **/
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {1,3};
        int[] res = searchRange(nums, 1);
    }

    public static int[] searchRange(int[] nums, int target) {
        int left = binarySearch(nums, target, true);
        int right = binarySearch(nums, target, false);
//        int l = nums[left] == target ? left : -1;
//        int r = nums[right - 1] == target ? right: -1;
        int[] rs = {left, right};
        return rs;
    }

    static int binarySearch(int[] nums, int target, boolean isLeft) {
        int l = 0;
        int r = nums.length - 1;
        int mid = 0;
        while (l < r) {
            mid = l + (r - l) / 2;
            if (isLeft) {
                if (nums[mid] < target) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                if (nums[mid] > target) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
        }
        if (nums[l] == target) {
            return l;
        }
        if (l > 0 && nums[l - 1] == target) {
            return l - 1;
        }
        return -1;

    }
}
