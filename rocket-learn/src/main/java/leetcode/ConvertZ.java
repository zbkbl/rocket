package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Z形变换
 * @author: Liuyang
 * @date: 2019-11-13 18:29
 **/
public class ConvertZ {

    public static void main(String[] args) {
//        System.out.println(getColumnNum(16, 4));
        String s = "A";
        convert(s, 1);
    }


    public static String convert(String s, int numRows) {
        if (s == null || s.length() == 0) return "";
        if (numRows == 1) return s;
        int l = s.length();
        int column = getColumnNum(l, numRows);
        char[][] bucket = new char[numRows][column];
        putChar(bucket, s, numRows, column);

        return outPutString(bucket);
    }

    private static int getColumnNum(int length, int row) {
        int b = row + row - 2;
        int i = length / b;
        int currentColumn = i * (row - 1);
        int j = length % b;
        int y;
        if (j >= row) {
            y = 1 + j % row;
        } else {
            y = j % row;
        }
        return currentColumn + y;
    }

    private static void putChar(char[][] bucket, String s, int row, int column) {
        int k = 0;
        int l = s.length();
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                if (i % (row - 1) == 0) {
                    bucket[j][i] = k < l ? s.charAt(k) : ' ';
                    k++;
                    continue;
                }
                if ((i + j) % (row - 1) == 0) {
                    bucket[j][i] = k < l ? s.charAt(k) : ' ';
                    k++;
                } else {
                    bucket[j][i] = ' ';
                }
            }
        }
    }

    private static void print(char[][] bucket) {
        int row = bucket.length;
        int column = bucket[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print("[" + bucket[i][j] + "]");
            }
            System.out.println();
        }
    }

    private static String outPutString(char[][] bucket) {
        StringBuilder s = new StringBuilder();
        int row = bucket.length;
        int column = bucket[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (bucket[i][j] != ' ') {
                    s.append(bucket[i][j]);
                }
            }
        }
        return s.toString();
    }

    public static String convert2(String s, int numRows) {
        if (numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int i = 0;
        int flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) flag = -flag;
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder sb :rows){
            res.append(sb);
        }
        return res.toString();
    }
}
