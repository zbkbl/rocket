package leetcode;

/**
 * @author liuyang
 * @description
 * @date 2021/03/03 15:05
 **/
public class DeleteRepeat {

    public static void main(String[] args) {
        int[] nums = {1,2};
        int index = search(nums,  2);
        System.out.print(index);
    }

    static int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int i = 0;
        while (i < nums.length - 1) {
            if (nums[i] > nums[i + 1]) {
                break;
            }
            i++;
        }

        int split = i + 1;
        if (split == nums.length) {
            return binarySearch(nums, 0, nums.length - 1, target);
        }
        int index = -1;
        if (nums[split] == target) {
            return split;
        }
        if (nums[nums.length - 1] >= target) {
            index = binarySearch(nums, split, nums.length - 1, target);
        } else if (nums[nums.length - 1] < target) {
            index = binarySearch(nums, 0, split, target);
        }
        return index;
    }


    static int binarySearch(int[] nums, int l, int r, int target) {
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid;
            } else {
                return mid;
            }
        }
        return nums[l] == target? l:-1;
    }

}
