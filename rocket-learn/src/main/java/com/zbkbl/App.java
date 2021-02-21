package com.zbkbl;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        test();
    }

    public static String simplifyPath(String path) {
        String[] s = path.split("/");
        Deque<String> stack = new LinkedList<>();
        for (String st : s) {
            if (st.equals(".") || st.isEmpty()) {
                continue;
            }
            if (st.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                    continue;
                } else {
                    continue;
                }
            }
            stack.push(st);
        }
        StringBuilder sb = new StringBuilder();
        if (stack.isEmpty()) {
            return "/";
        }
        Iterator<String> iterator = stack.iterator();
        while (iterator.hasNext()) {
            sb.insert(0, iterator.next());
            sb.insert(0, "/");
        }
        return sb.toString();
    }

    public static int get(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;

        int[][] left = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = j == 0 ? 1 : left[i][j - 1] + 1;
                }
            }
        }

        int ret = 0;
        for (int j = 0; j < n; j++) {
            int[] up = new int[m];
            int[] down = new int[m];

            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int k = m - 1; k >= 0; k--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[k][j]) {
                    stack.pop();
                }
                down[k] = stack.isEmpty() ? m : stack.peek();
                stack.push(k);
            }

            for (int i = 0; i < m; i++) {
                int height = down[i] - up[i] - 1;
                int width = left[i][j];
                ret = Math.max(ret, height * width);
            }
        }
        return ret;
    }

    public static List<List<Integer>> test() {
        TreeNode root = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        root.left = t2;
        root.right = t3;
        t2.left = t4;
        t3.right = t5;


        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        boolean flag = true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Deque<Integer> deque = new LinkedList<>();
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if(flag){
                    deque.offerFirst(node.val);
                }else {
                    deque.offerLast(node.val);
                }

                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            ret.add(new LinkedList<>(deque));
            flag = !flag;
        }

        System.out.println(ret);
        return ret;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void test3(TreeNode root, List<List<Integer>> list) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentsize = queue.size();
            for (int i = 0; i < currentsize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

            }
            list.add(level);
        }
    }

}
