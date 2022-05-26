package jv.com.cain.algorithm.linked_list;

import java.util.Stack;

@SuppressWarnings("JavaDoc")
public class MyLinkedList {
    public static void main(String[] args) {
        System.out.println("==========test Subject5 PrintRevertLinkedList==========");
        testSubject5PrintRevertLinkedList();
    }

    @SuppressWarnings("unused")
    static class Node {
        private int data;
        private Node next = null;

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
    static void subject5PrintRevertLinkedList(Node head) {
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

    static void subject5PrintRevertLinkedListByRecursion(Node head) {
        if (head != null) {
            if (head.getNext() != null) {
                subject5PrintRevertLinkedListByRecursion(head.getNext());
            }
            System.out.print(head.getData() + ", ");
        }
    }

    static void testSubject5PrintRevertLinkedList() {
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);
        subject5PrintRevertLinkedList(node1);
        System.out.println();
        subject5PrintRevertLinkedListByRecursion(node1);
    }
}
