package jv.com.cain.algorithm.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings({"unused", "JavaDoc"})
public class MyTree {
    public static HashMap<Integer, Integer> treeNodeIndex;

    public static void main(String[] args) {
        System.out.println("==========test Subject32 LeveOrderVisit==========");
        testSubject32LeveOrderVisit();
    }

    /**
     * refe: https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/solution/mian-shi-ti-07-zhong-jian-er-cha-shu-by-leetcode-s/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(logn)
     *
     * @param preOrder
     * @param inOrder
     * @return return root node of tree
     */
    public static TreeNode buildBinaryTree(int[] preOrder, int[] inOrder) {
        int length = inOrder.length;
        if (length == 0) {
            return null;
        }

        if (treeNodeIndex == null) {
            treeNodeIndex = new HashMap<>();
        }
        // for finding node index effectively
        for (int i = 0; i < length; i++) {
            treeNodeIndex.put(inOrder[i], i);
        }
        return buildBinaryTreeRecursive(preOrder, 0, length - 1, inOrder, 0, length - 1);
    }

    private static TreeNode buildBinaryTreeRecursive(int[] preOrder, int preLeft, int preRight, int[] inOrder, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }

        // index of root node in inorder
        int rootIndexInOder = treeNodeIndex.get(preOrder[preLeft]);
        // left sub tree size
        int leftSubSize = rootIndexInOder - inLeft;
        TreeNode root = new TreeNode(preOrder[preLeft]);
        // continue recursive
        root.left = buildBinaryTreeRecursive(preOrder, preLeft + 1, preLeft + leftSubSize, inOrder, inLeft, rootIndexInOder - 1);
        root.right = buildBinaryTreeRecursive(preOrder, preLeft + leftSubSize + 1, preRight, inOrder, rootIndexInOder + 1, inRight);
        return root;
    }

    static class TreeNode {
        public int data;
        public TreeNode left = null;
        public TreeNode right = null;

        public TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public TreeNode(int data) {
            this.data = data;
        }
    }

    /**
     * refe: https://leanote.com/note/59827b86ab6441231e000e18
     * Time complexity: O(m * n)
     * Spatial complexity: O(m)
     *
     * @param root
     * @param sub
     * @return
     */
    public static boolean subject26IsSubTree(TreeNode root, TreeNode sub) {
        boolean result = false;
        if (root != null && sub != null) {
            // find the same node
            if (root.data == sub.data) {
                result = match(root, sub);
            }
            // continue root left subtree and right subtree
            if (!result) {
                result = subject26IsSubTree(root.left, sub);
            }
            if (!result) {
                result = subject26IsSubTree(root.right, sub);
            }
        }
        return result;
    }

    private static boolean match(TreeNode root, TreeNode sub) {
        // check sub tree firstly
        if (sub == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (root.data != sub.data) {
            return false;
        }
        // continue left subtree and right subtree
        return match(root.left, sub.left) && match(root.right, sub.right);
    }

    /**
     * refe: https://leetcode.cn/problems/invert-binary-tree/solution/fan-zhuan-er-cha-shu-by-leetcode-solution/
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param root
     * @return
     */
    public static TreeNode subject27RevertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = subject27RevertTree(root.right);
        root.right = subject27RevertTree(root.left);
        return root;
    }

    /**
     * refe: https://leetcode.cn/problems/binary-tree-level-order-traversal/solution/er-cha-shu-de-ceng-xu-bian-li-by-leetcode-solution/
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param head
     */
    public static void subject32LeveOrderVisit(TreeNode head) {
        if (head == null) {
            return;
        }

        Queue<TreeNode> assistQueue = new LinkedList<>();
        assistQueue.offer(head);
        while (!assistQueue.isEmpty()) {
            int curLevelSize = assistQueue.size();
            // visit every level
            for (int i = 0; i < curLevelSize; i++) {
                TreeNode node = assistQueue.poll();
                assert node != null;
                System.out.print(node.data + ", ");
                // add leaf node to assistQueue when visiting
                if (node.left != null) {
                    assistQueue.offer(node.left);
                }
                if (node.right != null) {
                    assistQueue.offer(node.right);
                }
            }
            System.out.println();
        }
    }

    public static void testSubject32LeveOrderVisit() {
        TreeNode node7 = new TreeNode(11, null, null);
        TreeNode node6 = new TreeNode(9, null, null);
        TreeNode node5 = new TreeNode(7, null, null);
        TreeNode node4 = new TreeNode(5, null, null);
        TreeNode node3 = new TreeNode(10, node6, node7);
        TreeNode node2 = new TreeNode(6, node4, node5);
        TreeNode node1 = new TreeNode(8, node2, node3);
        subject32LeveOrderVisit(node1);
    }
}
