package com.zbkbl;

import java.util.Random;

/**
 * @author liuyang
 * @description
 * @date 2021/03/19 17:05
 **/
public class Box {

    private static final int n = 100;

    public static void main(String[] args) {
        boolean[] box = new boolean[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            int a = random.nextInt(2);
            box[i] = a == 0;
        }
//        boolean[] box = {true,true,false,true};
        print(box);

        adjust(box);
    }

    public static void adjust(boolean[] box) {
        int c = n;
        while (c > 1) {
            for (int i = 0; i < n; i++) {
                if (!box[i]) {
                    int a = i + 1;
                    switchBox(box, a == n ? 0 : a);
                }
            }
            print(box);
            c = valid(box);
        }

        System.out.println();
        if (c == 0) {
            print(box);
            return;
        }
        int point = 0;
        for (int i = 0; i < n; i++) {
            if (!box[i]) {
                point = i;
                break;
            }
        }
        int begin = (point + 2) % n;
        int count = (n - 1) / 3;
        int i = 0;
        while (i < count) {
            switchBox(box, begin);
            begin = (begin + 3) % n;
            i++;
        }
        System.out.println("全灭状态:");
        print(box);
        for (int j = 0; j < n; j++) {
            switchBox(box, j);
        }
        System.out.println("全亮状态:");
        print(box);

    }

    public static void switchBox(boolean[] box, int index) {
        if (index == 0) {
            box[n - 1] = !box[n - 1];
            box[index] = !box[index];
            box[index + 1] = !box[index + 1];
        } else if (index == n - 1) {
            box[index - 1] = !box[index - 1];
            box[index] = !box[index];
            box[0] = !box[0];
        } else {
            box[index + 1] = !box[index + 1];
            box[index - 1] = !box[index - 1];
            box[index] = !box[index];
        }

    }

    private static void print(boolean[] box) {
        for (int i = 0; i < n; i++) {
            System.out.print(box[i] + ",");
        }

        System.out.println();
    }

    private static int valid(boolean[] box) {
        int c = 0;
        for (int i = 0; i < n; i++) {
            if (!box[i]) {
                c += 1;
            }
        }
        return c;
    }
}
