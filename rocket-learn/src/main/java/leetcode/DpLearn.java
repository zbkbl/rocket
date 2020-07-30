package leetcode;

/**
 * @author liuyang
 * @description
 * @date 2020/07/27 10:24
 **/
public class DpLearn {

    public static void main(String[] args) {
//        long start1 = System.currentTimeMillis();
//        int result1 = test1(45);
//        System.out.println("test1: latency:" + (System.currentTimeMillis() - start1) + ", result: " + result1);
//        long start2 = System.currentTimeMillis();
//        int result2 = test2(45);
//        System.out.println("test2: latency:" + (System.currentTimeMillis() - start2) + ", result: " + result2);
//        long start3 = System.currentTimeMillis();
//        int result3 = test3(45);
//        System.out.println("test2: latency:" + (System.currentTimeMillis() - start3) + ", result: " + result3);

        int result4 = test4(3, 3);
        System.out.println(result4);
    }

    private static int test1(int n) {

        if (n == 1 || n == 2) {
            return 1;
        }
        return test1(n - 1) + test1(n - 2);
    }

    private static int test2(int n) {
        int[] num = new int[n + 1];
        num[0] = 1;
        num[1] = 1;
        for (int i = 2; i <= n; i++) {
            num[i] = num[i - 1] + num[i - 2];
        }
        return num[n - 1];
    }

    private static int test3(int n) {
        if (n == 1) return 1;
        if (n == 2) return 1;
        int a = 1, b = 1;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }

    private static int test4(int i, int j) {
        int[][] f = new int[i][j];
        for (int x = 0; x < i; x++) {
            for (int y = 0; y < j; y++) {
                if (x == 0 && y == 0) {
                    f[x][y] = 0;
                } else if(x==0 ||y ==0){
                    f[x][y] = 1;
                } else {
                    f[x][y] = f[x - 1][y] + f[x][y - 1];
                }
            }
        }
        return f[i - 1][j - 1];
    }
}
