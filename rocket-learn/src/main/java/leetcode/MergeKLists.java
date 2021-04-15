package leetcode;

/**
 * @author liuyang
 * @description 合并K个升序链表
 * @date 2021/03/15 23:32
 **/
public class MergeKLists {

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(7);
        ListNode node4 = new ListNode(9);
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        head1.next = node1;
        print(head1);
        ListNode head2 = new ListNode(2);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(11);
        ListNode node7 = new ListNode(13);
        ListNode node8 = new ListNode(15);
        head2.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        print(head2);
//        ListNode l = mergeTwoLists(head1, head2);
//        print(l);

        ListNode head3 = new ListNode(12);
        ListNode node9 = new ListNode(14);
        ListNode node10 = new ListNode(16);
        ListNode node11 = new ListNode(18);
        ListNode node12 = new ListNode(20);
        head3.next = node9;
        node9.next = node10;
        node10.next = node11;
        node11.next = node12;
        print(head3);

        ListNode[] input = new ListNode[3];
        input[0] = head1;
        input[1] = head2;
        input[2] = head3;
        ListNode ans = mergeKLists(input);
        print(ans);
    }

    private static ListNode mergeKLists(ListNode[] lists) {
        return mergeK(lists, 0, lists.length-1);
    }


    private static ListNode mergeK(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = l + (r - l) / 2;
        return mergeTwoLists(mergeK(lists, l, mid), mergeK(lists, mid+1, r));
    }

    private static ListNode mergeKListsForTest(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; i++) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }


    private static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        ListNode p = head1;
        ListNode q = head2;
        while (p != null && q != null) {
            if (p.val < q.val) {
                tail.next = p;
                p = p.next;
            } else {
                tail.next = q;
                q = q.next;
            }
            tail = tail.next;
        }
        tail.next = p == null ? q : p;
        return dummy.next;
    }

    static class ListNode {
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

    static void print(ListNode head) {
        ListNode ptr = head;
        while (ptr != null) {
            System.out.print(ptr.val + " -> ");
            ptr = ptr.next;
        }
        System.out.println();
    }
}
