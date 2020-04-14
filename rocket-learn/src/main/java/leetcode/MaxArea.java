package leetcode;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-11-22 15:35
 **/
public class MaxArea {
    public static void main(String[] args) {
        int[] a = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea1(a));
    }

    /**
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param height
     * @return
     */
    private static int maxArea(int[] height) {
        int area = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                area = Math.max(area, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return area;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     * @param height
     * @return
     */
    private static int maxArea1(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int area = 0;
        while (j > i) {
            area = Math.max(area, Math.min(height[i], height[j]) * (j - i));
            if(height[i] > height[j]){
                j--;
            } else{
                i++;
            }
        }
        return area;
    }
}
