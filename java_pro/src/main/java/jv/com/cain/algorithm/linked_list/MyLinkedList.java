package jv.com.cain.algorithm.linked_list;

import java.util.Deque;
import java.util.LinkedList;

@SuppressWarnings({"JavaDoc", "unused"})
public class MyLinkedList {
    public static void main(String[] args) {
        System.out.println("==========test Subject5 PrintRevertLinkedList==========");
        testSubject5PrintRevertLinkedList();
        System.out.println("==========test Subject52 GetFirstIntersectionNode==========");
        testSubject52GetFirstIntersectionNode();
        System.out.println("==========test Subject22 GetKthFromEnd==========");
        testSubject22GetKthFromEnd();
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("node[");
            sb.append("data =").append(data);
            sb.append(", next =").append(next.hashCode());
            sb.append("]");
            return sb.toString();
        }
    }

    /**
     * refe: https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/solution/mian-shi-ti-06-cong-wei-dao-tou-da-yin-lian-biao-b/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param head
     */
    public static void subject6PrintRevertLinkedList(Node head) {
        Deque<Node> stk = new LinkedList<>();
        Node temp = head;
        while (temp != null) {
            stk.push(temp);
            temp = temp.next;
        }

        while (!stk.isEmpty()) {
            Node node = stk.pop();
            if (node != null) {
                System.out.print(node.data + ", ");
            }
        }
    }

    public static void subject6PrintRevertLinkedListByRecursion(Node head) {
        if (head != null) {
            if (head.next != null) {
                subject6PrintRevertLinkedListByRecursion(head.next);
            }
            System.out.print(head.data + ", ");
        }
    }

    public static void testSubject5PrintRevertLinkedList() {
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);
        subject6PrintRevertLinkedList(node1);
        System.out.println();
        subject6PrintRevertLinkedListByRecursion(node1);
        System.out.println();
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
        if (node.next != null) {// node is not last node
            Node nextNode = node.next;

            node.data = nextNode.data;
            node.next = nextNode.next;

            nextNode.next = null;
            nextNode = null;
        } else if (node == head) {// node and head is last node
            // c++
            node = null;
            head = null;
        } else {// node is last node
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
        while (fast != null && k > 1) {
            fast = fast.next;
            k--;
        }
        // fast should not arrive at tail node
        if (fast == null) {
            return null;
        }
        // finish when fast arrive at tail node
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void testSubject22GetKthFromEnd() {
        Node node6 = new Node(6, null);
        Node node5 = new Node(5, node6);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);
        int k = 3;
        Node node = subject22GetKthFromEnd(node1, k);
        System.out.println(k + "th node from end: " + node.data);
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
            // store next domain to avoid dismiss
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

        // step3 divide
        node = head;
        Node newHead = head.next;
        while (node != null) {
            Node newNode = node.next;
            node.next = node.next != null ? node.next.next : null;
            assert newNode != null;
            newNode.next = newNode.next != null ? newNode.next.next : null;
            node = node.next;
        }
        return newHead;
    }

    /**
     * refe: https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/solution/liang-ge-lian-biao-de-di-yi-ge-gong-gong-pzbs/ method2
     * Time complexity: O(m + n)
     * Spatial complexity: O(1)
     *
     * @param headA
     * @param headB
     * @return
     */
    public static Node subject52GetFirstIntersectionNode(Node headA, Node headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Node pA = headA;
        Node pB = headB;
        while (pA != pB) {
            // switch linked list to visit continue when arriving at tail
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public static void testSubject52GetFirstIntersectionNode() {
        Node nodeA5 = new Node(7, null);
        Node nodeA4 = new Node(6, nodeA5);
        Node nodeA3 = new Node(3, nodeA4);
        Node nodeA2 = new Node(2, nodeA3);
        Node nodeA1 = new Node(1, nodeA2);

        Node nodeB2 = new Node(5, nodeA4);
        Node nodeB1 = new Node(4, nodeB2);
        System.out.println("linked list 1 and linked list 2 first intersection node: " + subject52GetFirstIntersectionNode(nodeA1, nodeB1));
    }

    /**
     * refe: https://leetcode-cn.com/problems/linked-list-cycle/solution/huan-xing-lian-biao-by-leetcode-solution/ method2
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param head
     * @return
     */
    public static boolean hasCircle(Node head) {
        if (head == null || head.next == null) {
            return false;
        }
        Node slow = head, fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast == slow) {
                return true;
            }
            fast = fast.next;
        }
        return true;
    }

    /**
     * refe: https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/ method1
     * Time complexity: O(n^2*k)
     * Spatial complexity: O(1)
     *
     * @param lists
     * @return
     */
    public static Node mergeKList(Node[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        Node mergedHead = lists[0];
        int len = lists.length;
        for (int i = 1; i < len; i++) {
            mergedHead = mergeTwoList(mergedHead, lists[i]);
        }
        return mergedHead;
    }

    /**
     * refe: https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/ method2
     * Time complexity: O(n*logn*k)
     * Spatial complexity: O(logn)
     *
     * @param lists
     * @return
     */
    public static Node mergeKList2(Node[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKListRecursive(lists, 0, lists.length - 1);
    }

    /**
     * merge lists recursively
     *
     * @param lists
     * @param left  start index
     * @param right end index
     * @return return merged list
     */
    private static Node mergeKListRecursive(Node[] lists, int left, int right) {
        if (left == right) return lists[left];
        if (left > right) return null;
        // binary divide
        int mid = left + ((right - left) >> 1);
        return mergeTwoList(mergeKListRecursive(lists, left, mid), mergeKListRecursive(lists, mid + 1, right));
    }

    /**
     * merge two sorted linked list
     *
     * @param list1
     * @param list2
     * @return return
     */
    private static Node mergeTwoList(Node list1, Node list2) {
        Node curNode = new Node();
        Node mergedHead = curNode;

        while (list1 != null && list2 != null) {
            // merge lists
            if (list1.data <= list2.data) {
                curNode.next = list1;
                list1 = list1.next;
            } else {
                curNode.next = list2;
                list2 = list2.next;
            }
            curNode = curNode.next;
        }

        // handle the remaining
        if (list1 != null) {
            curNode.next = list1;
        }
        if (list2 != null) {
            curNode.next = list2;
        }

        return mergedHead.next;
    }

    /**
     * refe: https://leetcode-cn.com/problems/merge-two-sorted-lists/
     * Time complexity: O(m+n)
     * Spatial complexity: O(1)
     *
     * @param list1
     * @param list2
     * @return
     */
    public static Node mergeSortedLinkedList(Node list1, Node list2) {
        Node head = new Node();
        Node cur = head;
        while (list1 != null && list2 != null) {
            // merge list
            if (list1.data < list2.data) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        // handle remain list part
        if (list1 != null) {
            cur.next = list1;
        } else {
            cur.next = list2;
        }
        // return merged list head
        return head.next;
    }

    /**
     * refe: https://leetcode-cn.com/problems/middle-of-the-linked-list/ method3
     * Time complexity: O(size)
     * Spatial complexity: O(1)
     *
     * @param head
     * @return
     */
    public static Node findMidNode(Node head) {
        Node oneStepHead = head;
        Node twoStepHead = head;
        while (twoStepHead != null && twoStepHead.next != null) {
            oneStepHead = oneStepHead.next;
            twoStepHead = twoStepHead.next.next;
        }
        return oneStepHead;
    }
}
