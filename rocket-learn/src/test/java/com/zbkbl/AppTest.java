package com.zbkbl;

import abstractFactory.*;
import abstractFactory.singleton.Singleton;
import abstractFactory.staticFactory.Sender;
import com.google.common.collect.Lists;
import builder.Meal;
import builder.MealBuilder;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.aviator.AviatorEvaluator;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void test() {
        AbstractFactory shapeFactory = FactoryProducer.getFactory("shape");
        Shape shape1 = shapeFactory.getShape("circle");
        shape1.draw();

        AbstractFactory colorFactory = FactoryProducer.getFactory("color");
        Color color = colorFactory.getColor("red");
        color.fill();
    }

    @Test
    public void test1() {
        Provider p = new SendMailFactory();
        Sender s = p.produce();
        s.send();
    }

    @Test
    public void test2() {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " + vegMeal.getCost());


        Meal nonVegMeal = mealBuilder.prepareNonvegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " + nonVegMeal.getCost());
    }

    @Test
    public void test4() {
        System.out.println(Singleton.getInstance());
    }


    @Test
    public void test5() {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        Set set1 = new HashSet();
        for (Integer i : set) {
            set1.add(i);
        }

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer a = iterator.next();
            if (a == 1) {
                iterator.remove();
            }
        }

        System.out.println(set);
        System.out.println(set1);
    }


    @Test
    public void test6() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "liuyang");
        jsonObject.put("filterInfo", "test");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "liuyang1");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", "liuyang2");
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("name", "liuyang3");
        List<JSONObject> filterInfos = new ArrayList<>();
        filterInfos.add(jsonObject);
        filterInfos.add(jsonObject1);
        filterInfos.add(jsonObject2);
        filterInfos.add(jsonObject3);
        for (JSONObject json : filterInfos) {
            json.remove("filterInfo");
        }
        System.out.println(filterInfos);
    }

    @Test
    public void test7() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 5) {
                    break;
                }
                System.out.println(i * j);
            }
        }
    }

//    @Test
//    public void test8() {
//        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
//            System.out.println("跑!");
//        });
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(20));
//        for (int i = 0; i < 5; i++) {
//            int x = i;
//            executor.submit(() -> {
//                try {
//                    Thread.sleep(1000);
//                    System.out.println(Thread.currentThread().getName() + "===" + "线程" + x + "准备完毕!");
//                    barrier.await();
//                    System.out.println(Thread.currentThread().getName() + "===" + "线程" + x + "已经起跑!");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//        try {
//            Thread.currentThread().join();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    @Test
    public void test11() {
        int[] a = {3, 2, 1, 5, 6, 4};
//        findKthLargest(a, 2);
        partition(a, 0, a.length - 1);
        System.out.println(a);
    }

    public static int findKthLargest(int[] nums, int k) {
        int size = nums.length;
        buildHeap(nums, size);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            int tmp = nums[0];
            nums[0] = nums[i];
            nums[i] = tmp;
            size -= 1;
            adjust(nums, 0, size);
        }
        return nums[0];
    }

    public static void buildHeap(int[] nums, int size) {
        for (int i = size / 2; i >= 0; i--) {
            adjust(nums, i, size);
        }
    }

    public static void adjust(int[] heap, int n, int size) {
        int l = 2 * n + 1;
        int r = 2 * n + 2;
        int largest = n;
        if (l < size && heap[l] > heap[largest]) {
            largest = l;
        }

        if (r < size && heap[r] > heap[largest]) {
            largest = r;
        }

        if (largest != n) {
            int tmp = heap[n];
            heap[n] = heap[largest];
            heap[largest] = tmp;
            adjust(heap, largest, size);
        }
    }

    static int quickSort(int[] nums, int l, int r) {
        int i = l;
        int j = r;
        int x = nums[i];
        while (i < j) {
            while (i < j && nums[j] >= x) {
                j--;
            }
            if (i < j) {
                nums[i++] = nums[j];
            }
            while (i < j && nums[i] < x) {
                i++;
            }
            if (i < j) {
                nums[j--] = nums[i];
            }
        }
        nums[i] = x;
        return i;
    }

    static void partition(int[] nums, int l, int r) {
        if (l < r) {
            int i = quickSort(nums, l, r);
            partition(nums, l, i - 1);
            partition(nums, i + 1, r);
        }
    }

    @Test
    public void test22() {
        int[] nums = {1, 0, -1, 0, -2, 2};
        System.out.println(fourSum(nums, 0));
    }


    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        int n = nums.length;
        if (n < 4 || nums[0] + nums[1] + nums[2] + nums[3] > target ||
                nums[n - 1] + nums[n - 2] + nums[n - 3] + nums[n - 4] < target) {
            return list;
        }
        backTrace(nums, 0, 0, target, list, new LinkedList<>(), nums.length);
        return list;
    }

    void backTrace(int[] nums, int low, int sum, int tar, List<List<Integer>> list, Deque<Integer> subList, int size) {
        if (subList.size() == 4) {
            if (sum == tar) {
                list.add(new ArrayList<>(subList));
            }
            return;
        }
        for (int i = low; i < size; i++) {
            if (size - i < 4 - subList.size()) {
                return;
            }
            if (i > low && nums[i] == nums[i - 1]) {
                continue;
            }
            if (i < size - 1 && sum + nums[i] + (3 - subList.size()) * nums[i + 1] > tar) {
                return;
            }
            if (i < size - 1 && sum + nums[i] + (3 - subList.size()) * nums[size - 1] < tar) {
                continue;
            }
            sum += nums[i];
            subList.push(nums[i]);
            backTrace(nums, i + 1, sum, tar, list, subList, size);
            subList.pop();
            sum -= nums[i];

        }
    }


    @Test
    public void dpBag() {
        int[] w = {1, 4, 3};
        int[] v = {15, 30, 20};
        int W = 4;
        System.out.println(maxValue(w, v, W));
    }

    public int maxValue(int[] weight, int[] value, int W) {
        int n = weight.length;
        if (n == 0) {
            return 0;
        }
        //dp[i][k] 表示前i个物品在k容量下的最大价值
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= W; k++) {
                // k - weight[i-1] 放入当前物品后剩余的容量
                // dp[i-1][k - weight[i-1]] 前 i-1 个物品在放入当前物品后的剩余容量下的最大价值
                int withValue = k - weight[i - 1] >= 0 ? (value[i - 1] + dp[i - 1][k - weight[i - 1]]) : 0;
                int withOutValue = dp[i - 1][k];
                dp[i][k] = Math.max(withValue, withOutValue);
            }
        }
        return dp[n][W];
    }

    @Test
    public void debug() {
//        ListNode head = new ListNode(0);
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        test(head, 0);
    }

    public ListNode test(ListNode head, int k){
        ListNode p = head;
        ListNode q = head;
        if(head == null) return head;
        int length = 0;
        while (p != null) {
            length += 1;
            p = p.next;
        }
        p = head;
        int size = k % length;
        if(size == 0) return head;
        for (int i = 0; i < size; i++) {
            if (q.next != null) {
                q = q.next;
            }
        }

        for (int i = 0; i < size; i++) {
            if (q.next != null) {
                q = q.next;
                p = p.next;
            }
        }
        q.next = head;
        head = p.next;
        p.next = null;
        return head;
    }
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    int I = 0;

//    @Test
//    public void testt(){
//
//        String s = "qwe";
//        System.out.println(permutation(s));
//
//    }
//
//    public String[] permutation(String S) {
//        int N = getLength(S.length());
//        String[] ans = new String[N];
//        char[] chars = S.toCharArray();
//        backTrace(ans, chars,0);
//        return ans;
//    }
//
//    void backTrace(String[] res, char[] ch, int index) {
//        if (index == ch.length - 1) {
//            res[I++] = String.valueOf(ch);
//            return;
//        }
//
//        for (int i = 0; i < ch.length; i++) {
//            swap(ch, index, i);
//            backTrace(res, ch, index + 1);
//            swap(ch, index, i);
//        }
//
//    }
//
//    int getLength(int n) {
//        if (n == 1) {
//            return 1;
//        }
//        return n * getLength(n - 1);
//
//    }
//
//    void swap(char[] ch, int i, int j){
//        char cc = ch[i];
//        ch[i] = ch[j];
//        ch[j] = cc;
//    }

    @Test
    public void test3(){
        System.out.println(combine(4,2));
    }
    List<List<Integer>> ans = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        backTrace(new LinkedList<>(), 1, n ,k);
        return ans;
    }

    void backTrace(List<Integer> cur, int index, int n, int k) {
        if (cur.size() == k) {
            List<Integer> list = new LinkedList<>();
            cur.forEach(c->list.add(c));
            ans.add(list);
            return;
        } else {
            for (int i = index; i < n; i++) {
                cur.add(i);
                backTrace(cur, index + 1, n, k);
                cur.remove(cur.size() - 1);
            }
        }
    }

    @Test
    public void testjson(){
        String s = "[{\"database\":\"xxxx\",\"table\":\"xxx\",\"fields\":[\"xxx\",\"xxx\"]},{\"database\":\"xxxx\",\"table\":\"xxx\",\"fields\":[\"xxx\",\"xxx\"]}]";
        JSONArray jsonArray = JSONArray.parseArray(s);
        Iterator<Object> iterator = jsonArray.stream().iterator();
        while (iterator.hasNext()){
            Object o = iterator.next();
            System.out.println(o);
        }
    }

    @Test
    public void testFilter(){
        List<Integer> list = Lists.newArrayList(1,2,3,4,5);
        List<Integer> collect = list.stream().filter(i -> i == 1).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void testJson(){
        JSONObject uiInfo = new JSONObject();
        String kolFaceToFaceInviteUIInfo = "{\n" +
                "\t\"step1\": {\n" +
                "\t\t\"mainTitle\": \"第1步：保存推广海报\",\n" +
                "\t\t\"comment\": \"专属拉新二维码，新人扫码下单后你可获得返现～\",\n" +
                "\t\t\"posterPicUrl\": \"https://1232.com\",\n" +
                "\t\t\"buttonTitle\": \"保存海报\"\n" +
                "\t},\n" +
                "\t\"step2\": {\n" +
                "\t\t\"mainTitle\": \"第2步：复制推广文案\",\n" +
                "\t\t\"comment\": \"好的文案可以吸引新人更快下单哦～\",\n" +
                "\t\t\"copywriting\": [\"我发现了一个超级实惠的外卖平台，美团做的，正在大力补贴，一顿饭只要几块钱，太划算了。微信扫码直接下单，快来试试吧！\", \"美团出了一个新的外卖平台，主打优惠，全场均价5.9元，还不需要配送费，太便宜了。微信扫码直接下单，赶紧来试试吧！\", \"美团这次大手笔！外卖超值福利大补贴，每人限领一次，全场外卖几块钱就可以吃，还有机会免单不要钱，太猛了。微信扫码直接下单，赶紧来抢！\", \"今天占了一个大便宜，美团出了新的外卖平台，大力补贴，一顿外卖只花了我五块多，说是每人只有一次机会，赶紧来抢吧。微信扫码可以直接下单，很方便！\"],\n" +
                "\t\t\"buttonTitle\": \"复制文案\"\n" +
                "\t},\n" +
                "\t\"step3\": {\n" +
                "\t\t\"mainTitle\": \"第3步：发朋友圈\",\n" +
                "\t\t\"comment\": \"用保存的海报和复制的文案发朋友圈，邀请朋友圈里的好友下单。\\n\\n经过测试，多发几次朋友圈拉新效果更好，赚钱更多哦～\",\n" +
                "\t\t\"frendsPicUrl\": \"https://1232.com\",\n" +
                "\t\t\"buttonTitle\": \"现在就去发\"\n" +
                "\t}\n" +
                "}";
        String kolFriendsUIInfo = "{\n" +
                "\t\"top\": \"超值外卖福利\",\n" +
                "\t\"main\": \"首单全额返现金\",\n" +
                "\t\"sub\": \"免配送费\",\n" +
                "\t\"guide\": \"微信扫码立即下单\\n首单可领「免单」福利\"\n" +
                "}";
        // 组装kol UI信息
        uiInfo.put("kolFaceToFaceInviteUIInfo", JSONObject.parse(kolFaceToFaceInviteUIInfo));
        uiInfo.put("kolFriendsUIInfo", JSONObject.parse(kolFriendsUIInfo));
        String result = JSONObject.toJSONString(uiInfo);
        System.out.println(result);
    }
}
