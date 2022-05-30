package jv.com.cain.algorithm.tree;
import java.util.HashMap;

@SuppressWarnings({"unused", "JavaDoc"})
public class MyTree {
    public static HashMap<Integer, Integer> treeNodeIndex;

    public static void main(String[] args) {

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
}