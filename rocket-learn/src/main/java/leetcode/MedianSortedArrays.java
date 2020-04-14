package leetcode;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-07-16 15:14
 **/
public class MedianSortedArrays {

    public static void main(String[] args) {
        int[] num1 = {1, 3, 5};
        int[] num2 = {2, 4, 7};
        double r = findMedianSortedArrays(num1, num2);
        System.out.println(r);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int x = nums1.length, y = nums2.length;
        int[] nums = new int[x + y];
        while (i < x || j < y) {
            if (i == x) {
                nums[i + j] = nums2[j++];
            } else if (j == y) {
                nums[i + j] = nums1[i++];
            } else {
                nums[i + j] = nums1[i] <= nums2[j] ? nums1[i++] : nums2[j++];
            }
        }
        double r;
        if ((x + y) % 2 == 0) {
            r = (((double) nums[(x + y) / 2] + (double) nums[(x + y - 1) / 2])) / 2;
        } else {
            r = nums[(x + y) / 2];
        }
        return r;
    }

    public static void printNum(int[] num) {
        for (int i = 0; i < num.length; i++) {
            System.out.println(num[i]);
        }
    }
}
