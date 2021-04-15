package leetcode;

/**
 * @author liuyang
 * @description
 * @date 2021/03/03 14:14
 **/
public class Adjust {
    public static void main(String[] args) {
        int[] sample = {2, 7, 4, 5, 6, 3, 8, 1, 10};
        Node dummy = new Node(-1);
        Node cur = dummy;
        for (int i = 0; i < sample.length; i++) {
            Node node = new Node(sample[i]);
            cur.next = node;
            cur = cur.next;
        }
//        print(dummy.next);
//        Node head = reverse(dummy.next);
        test(dummy.next);
    }

    private static Node test(Node head) {
        Node dummy1 = new Node(-1);
        Node dummy2 = new Node(-1);
        Node tail1 = dummy1;
        Node tail2 = dummy2;
        Node cur = head;
        while (cur != null && cur.next != null) {
            tail1.next = cur;
            tail1 = tail1.next;
            tail2.next = cur.next;
            tail2 = tail2.next;
            cur = cur.next.next;
        }
        tail1.next = cur;
        tail2.next = null;
        Node head1 = dummy1.next;
        Node head2 = reverse(dummy2.next);

        print(head1);
        print(head2);
        Node l = merge(head1, head2);
        print(l);
        return l;
    }


    private static Node merge(Node head1, Node head2) {
        Node dummy = new Node(-1);
        Node tail = dummy;
        Node l1 = head1;
        Node l2 = head2;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    private static Node reverse(Node head) {
        Node dummy = new Node(-1);
        Node p = head;
        while (p != null) {
            Node q = p.next;
            p.next = dummy.next;
            dummy.next = p;
            p = q;
        }
//        print(dummy.next);
        return dummy.next;
    }

    private static void print(Node head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }

        System.out.println();
    }

    private static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }
}
