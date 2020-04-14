package test1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @description:
 * @author: Liuyang
 * @date: 2019-06-27 11:56
 **/
public class StringHashCodeTest {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode result = addTwoNumbers(l1,l2);
        System.out.println(result);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int x = 0;
        ListNode o = l1;
        ListNode p = l2;
        ListNode result = new ListNode(0);
        ListNode z = result;
        while (o != null || p != null) {
            ListNode y;
            int val;
            if(o == null) {
                val = p.val + x;
                p = p.next;
            }else if(p == null){
                val = o.val + x;
                o = o.next;
            }else {
                val = o.val + p.val + x;
                o = o.next;
                p = p.next;
            }

            if (val >= 10) {
                y = new ListNode(val % 10);
                x = 1;
            } else {
                y = new ListNode(val);
                x = 0;
            }
            z.next = y;
            z = z.next;
        }
        if(x != 0){
            z.next = new ListNode(1);
        }
        return result.next;
    }
}
