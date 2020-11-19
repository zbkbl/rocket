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
//        double r = findMedianSortedArrays(num1, num2);
//        System.out.println(r);
        printNum(sort(num1, num2));
        int n = findMedianNum(num1, num2);
        System.out.println(n);
    }


    // 双指针合并两个有序数组
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
        System.out.print("[");
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i]);
            System.out.print("  ");
        }
        System.out.print("]");
        System.out.println();
    }

    private static int[] sort(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int[] result = new int[l1 + l2];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < l1 || j < l2) {
            if (i >= l1) {
                result[k++] = nums2[j++];
            } else if (j >= l2) {
                result[k++] = nums1[i++];
            } else {
                if (nums1[i] <= nums2[j]) {
                    result[k++] = nums1[i++];
                } else {
                    result[k++] = nums2[j++];
                }
            }
        }
        return result;
    }

    // 递归寻找中位数,两个长度相同的有序数组

    private static int findMedianNum(int[] num1, int[] num2) {
        if (num1 == null || num2 == null) {
            return -1;
        }

        return find(num1, 0, num1.length, num2, 0, num2.length);
    }

    private static int find(int[] num1, int L1, int R1, int[] num2, int L2, int R2) {
        int mid1 = L1 + (R1 - L1 - 1) / 2;
        int mid2 = L2 + (R2 - L2 - 1) / 2;
        if (L1 >= R1)
            return Math.min(num1[L1], num2[L2]);

        int offset = ((R1 - L1) & 1) ^ 1; // 偏移量
        if (num1[mid1] > num2[mid2]) {
            return find(num1, L1, mid1, num2, mid2 + offset, R2);
        } else if (num1[mid1] < num2[mid2]) {
            return find(num1, mid1 + offset, R2, num2, L2, mid2);
        } else {
            return num1[mid1];
        }

    }
}
