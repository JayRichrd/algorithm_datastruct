package jv.com.cain.algorithm.linked_list;

import java.util.Stack;

@SuppressWarnings({"JavaDoc", "unused"})
public class MyLinkedList {
    public static void main(String[] args) {
        System.out.println("==========test Subject5 PrintRevertLinkedList==========");
        testSubject5PrintRevertLinkedList();
    }

    @SuppressWarnings("unused")
    static class Node {
        public int data;
        public Node next = null;
        public Node random = null;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    /**
     * refe: https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/solution/mian-shi-ti-06-cong-wei-dao-tou-da-yin-lian-biao-b/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param head
     */
    public static void subject5PrintRevertLinkedList(Node head) {
        Stack<Node> stk = new Stack<>();
        Node temp = head;
        while (temp != null) {
            stk.push(temp);
            temp = temp.getNext();
        }

        do {
            Node top = stk.pop();
            if (top != null) {
                System.out.print(top.getData() + ", ");
            }
        } while (!stk.isEmpty());
    }

    public static void subject5PrintRevertLinkedListByRecursion(Node head) {
        if (head != null) {
            if (head.getNext() != null) {
                subject5PrintRevertLinkedListByRecursion(head.getNext());
            }
            System.out.print(head.getData() + ", ");
        }
    }

    public static void testSubject5PrintRevertLinkedList() {
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);
        subject5PrintRevertLinkedList(node1);
        System.out.println();
        subject5PrintRevertLinkedListByRecursion(node1);
    }

    /**
     * refe: https://leanote.com/note/59827b86ab6441231e000e18
     *
     * @param head
     * @param node
     */
    public static void subject18DeleteNode(Node head, Node node) {
        if (head == null || node == null) {
            return;
        }
        if (node.next != null) {
            Node nextNode = node.next;
            node.data = nextNode.data;
            node.next = nextNode.next;
            nextNode.next = null;
        } else if (node == head) {
            // c++
            node.next = null;
            head.next = null;
        } else {
            Node preNode = head;
            while (preNode.next != node) {
                preNode = preNode.next;
            }
            preNode.next = null;
        }
    }

    /**
     * refe: https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/solution/lian-biao-zhong-dao-shu-di-kge-jie-dian-1pz9l/ method2
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param head
     * @param k
     * @return
     */
    public static Node subject22GetKthFromEnd(Node head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        Node fast = head;
        Node slow = head;
        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }
        if (k > 0) {
            return null;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * refe: https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/solution/fan-zhuan-lian-biao-by-leetcode-solution-jvs5/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param head
     * @return
     */
    public static Node subject24RevertLinkedList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            // revert
            cur.next = pre;
            // be careful, update pre first, and then update cur
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * refe: https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/solution/he-bing-liang-ge-pai-xu-de-lian-biao-by-g3z6g/ method2
     * Time complexity: O(m + n)
     * Spatial complexity: O(1)
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node subject25MergeSortedLinkedList(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        // temp head node
        Node head = new Node();
        Node cur = head;
        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        cur.next = head1 == null ? head2 : head1;
        return head.next;
    }

    /**
     * refe: https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/solution/fu-za-lian-biao-de-fu-zhi-by-leetcode-so-9ik5/ method2
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param head
     * @return
     */
    public static Node subject35CopyLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        // step1 construct node and insert behind
        Node node = head;
        while (node != null) {
            Node newNode = new Node();
            newNode.data = node.data;
            newNode.next = node.next;
            node.next = newNode;
            // be careful
            node = node.next.next;
        }

        // step2 assign random
        node = head;
        while (node != null) {
            Node newNode = node.next;
            newNode.random = node.random == null ? null : node.random.next;
            node = node.next.next;
        }

        Node newHead = head.next;
        node = head;
        // step3 divide
        while (node != null) {
            Node newNode = node.next;
            node.next = node.next != null ? node.next.next : null;
            newNode.next = newNode.next != null ? newNode.next.next : null;
            node = node.next;
        }
        return newHead;
    }

}
