package leetcode;

/**
 * @description: Add Two Numbers
 * @author: Liuyang
 * @date: 2019-10-14 15:01
 **/
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode head = new ListNode(5);
//        head.next = new ListNode(4);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(6);
        ListNode tail = new ListNode(5);
//        tail.next = new ListNode(6);
//        tail.next.next = new ListNode(4);
        System.out.println(head);
        System.out.println(tail);
//        System.out.println(reverseLinkTabel(head));
//        System.out.println(reverseLinkTabel(tail));
        System.out.println(addTwoNumbers(head, tail));
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur = new ListNode(0);
        ListNode rl = cur;
        int c = 0;
        ListNode p = l1;
        ListNode q = l2;
        while (p != null || q != null) {
            ListNode newNode;
            int x = p != null ? p.val : 0;
            int y = q != null ? q.val : 0;
            int sum = c + x + y;
            newNode = new ListNode(sum % 10);
            c = sum >= 10 ? 1 : 0;
            cur.next = newNode;
            cur = cur.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (c != 0) {
            cur.next = new ListNode(1);
        }
        return rl.next;
    }


    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    private static ListNode reverseLinkTabel(ListNode head) {
        if (head == null) return null;
        ListNode pre = head;
        ListNode cur = head.next;
        ListNode tmp = null;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        head.next = null;
        return pre;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val +
                    "->" + next;
        }
    }
}
