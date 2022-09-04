package jv.com.cain.algorithm.tree;

import java.util.*;

@SuppressWarnings({"unused", "JavaDoc", "DuplicatedCode"})
public class MyTree {
    public static HashMap<Integer, Integer> treeNodeIndex;

    public static void main(String[] args) {
        System.out.println("==========test Subject32 LeveOrderVisit==========");
        testSubject32LeveOrderVisit();
        System.out.println("==========test Subject33 isBstPostVisit==========");
        testSubject33isBstPostVisit();
        System.out.println("==========test Subject34 SumPath==========");
        testSubject34SumPath();
        System.out.println("==========test Subject40 GetLeastNumbers==========");
        testSubject40GetLeastNumbers();
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
    public static TreeNode subject7BuildBinaryTree(int[] preOrder, int[] inOrder) {
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

    /**
     * check if root tree contains sub tree
     *
     * @param root
     * @param sub
     * @return
     */
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

        Queue<TreeNode> helpQueue = new LinkedList<>();
        helpQueue.offer(head);

        while (!helpQueue.isEmpty()) {
            int curLevelSize = helpQueue.size();
            // visit every level
            for (int i = 0; i < curLevelSize; i++) {
                TreeNode node = helpQueue.poll();
                assert node != null;
                System.out.print(node.data + ", ");
                // add leaf node to helpQueue when visiting
                if (node.left != null) {
                    helpQueue.offer(node.left);
                }
                if (node.right != null) {
                    helpQueue.offer(node.right);
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

    /**
     * refe: https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/mian-shi-ti-33-er-cha-sou-suo-shu-de-hou-xu-bian-6/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param tree
     * @return
     */
    public static boolean subject33isBstPostVisit(int[] tree) {
        int len = tree.length;
        if (len <= 0) {
            return false;
        }
        return isBstPostVisitRecursive(tree, 0, tree.length - 1);
    }

    private static boolean isBstPostVisitRecursive(int[] tree, int left, int right) {
        if (left >= right) {
            return true;
        }
        int pivot = left;
        while (tree[pivot] < tree[right]) pivot++;
        int div = pivot;
        while (tree[pivot] > tree[right]) pivot++;
        return pivot == right/*the key point*/ && isBstPostVisitRecursive(tree, left, div - 1) && isBstPostVisitRecursive(tree, div, right - 1);
    }

    public static void testSubject33isBstPostVisit() {
        int[] tree1 = {5, 7, 6, 9, 11, 10, 8};
        int[] tree2 = {7, 4, 6, 5};
        System.out.println("tree1 is BST post order: " + subject33isBstPostVisit(tree1));
        System.out.println("tree2 is BST post order: " + subject33isBstPostVisit(tree2));
    }

    /**
     * refe: https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/solution/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-68dg/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(logn)
     *
     * @param head
     * @param target
     * @return
     */
    public static List<List<Integer>> subject34SumPath(TreeNode head, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (head == null) {
            return result;
        }
        Deque<Integer> sumPath = new ArrayDeque<>();
        sumPathDfsRecursive(head, target, result, sumPath);
        return result;
    }

    private static void sumPathDfsRecursive(TreeNode node, int target, List<List<Integer>> result, Deque<Integer> sumPath) {
        if (node == null) {
            return;
        }
        // offer data firstly
        sumPath.offerLast(node.data);
        target -= node.data;
        // find leaf node and check
        if (node.left == null && node.right == null && target == 0) {
            result.add(new LinkedList<>(sumPath));
            return;
        }

        sumPathDfsRecursive(node.left, target, result, sumPath);
        sumPathDfsRecursive(node.right, target, result, sumPath);
        // pop and continue
        sumPath.pollLast();
    }

    public static void testSubject34SumPath() {
        TreeNode node7 = new TreeNode(11, null, null);
        TreeNode node6 = new TreeNode(9, null, null);
        TreeNode node5 = new TreeNode(7, null, null);
        TreeNode node4 = new TreeNode(5, null, null);
        TreeNode node3 = new TreeNode(10, node6, node7);
        TreeNode node2 = new TreeNode(6, node4, node5);
        TreeNode node1 = new TreeNode(8, node2, node3);
        int target = 21;
        List<List<Integer>> result = subject34SumPath(node1, target);
        for (List<Integer> path : result) {
            System.out.print("sumPath: ");
            for (Integer node : path) {
                System.out.print(node + ", ");
            }
            System.out.println();
        }
    }

    /**
     * refe: https://leanote.com/note/59827b86ab6441231e000e18
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param root
     * @return
     */
    public static TreeNode subject36Bst2LinkedList(TreeNode root) {
        // linked list tail node, use array as c++ pointer
        TreeNode[] tail = new TreeNode[1];
        tail[0] = null;
        midConvertRecursive(root, tail);

        if (tail[0] == null) {
            return null;
        }

        // find head node
        TreeNode head = tail[0];
        while (head.left != null) {
            head = head.left;
        }

        return head;
    }

    /**
     * middle order visit
     *
     * @param root
     * @param tail
     */
    private static void midConvertRecursive(TreeNode root, TreeNode[] tail) {
        if (root == null) {
            return;
        }

        // handle left sub tree
        if (root.left != null) {
            midConvertRecursive(root.left, tail);
        }

        // link root and tail node, change root node as tail node
        root.left = tail[0];
        if (tail[0] != null) {
            tail[0].right = root;
        }
        tail[0] = root;

        // handle right sub tree
        if (root.right != null) {
            midConvertRecursive(root.right, tail);
        }
    }

    /**
     * refe: https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof/solution/xu-lie-hua-er-cha-shu-by-leetcode-soluti-4duq/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param root
     * @return
     */
    public static String serializeTree(TreeNode root) {
        StringBuilder serializeSb = new StringBuilder();
        return doSerializeRecursive(root, serializeSb).toString();
    }

    private static StringBuilder doSerializeRecursive(TreeNode root, StringBuilder serializeSb) {
        if (root == null) {
            serializeSb.append("None,");
        } else {
            serializeSb.append(root.data).append(",");
            serializeSb = doSerializeRecursive(root.left, serializeSb);
            serializeSb = doSerializeRecursive(root.right, serializeSb);
        }
        return serializeSb;
    }

    /**
     * refe: https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof/solution/xu-lie-hua-er-cha-shu-by-leetcode-soluti-4duq/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param treeStr
     * @return
     */
    public static TreeNode rSerializeTree(String treeStr) {
        String[] dataArray = treeStr.split(",");
        List<String> dataList = Arrays.asList(dataArray);
        return doRserializeRecursive(dataList);
    }

    private static TreeNode doRserializeRecursive(List<String> dataList) {
        if (dataList.isEmpty() || dataList.get(0).equals("None")) {
            return null;
        }
        String data = dataList.get(0);
        dataList.remove(0);
        TreeNode root = new TreeNode(Integer.parseInt(data));
        root.left = doRserializeRecursive(dataList);
        root.right = doRserializeRecursive(dataList);
        return root;
    }

    /**
     * refe: https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/solution/zui-xiao-de-kge-shu-by-leetcode-solution/ method5
     * Time complexity: O(n * logk)
     * Spatial complexity: O(k)
     *
     * @param nums
     * @param k
     * @return
     */
    @SuppressWarnings("ConstantConditions")
    public static List<Integer> subject40GetLeastNumbers(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        if (k == 0) {
            return null;
        }

        int len = nums.length;
        List<Integer> result = new ArrayList<>();
        if (k >= len) {
            for (int num : nums) {
                result.add(num);
            }
            return result;
        }
        // default is small heap, so use our custom comparator
        PriorityQueue<Integer> largeHeap = new PriorityQueue<>(k, (num1, num2) -> num2 - num1);
        // fill largeHeap firstly
        for (int i = 0; i < k; i++) {
            largeHeap.offer(nums[i]);
        }
        for (int i = k; i < len; i++) {
            // find less element to replace
            if (nums[i] < largeHeap.peek()) {
                largeHeap.poll();
                largeHeap.offer(nums[i]);
            }
        }
        // result is descending
        for (int i = 0; i < k; i++) {
            result.add(largeHeap.poll());
        }
        Collections.reverse(result);
        return result;
    }

    public static void testSubject40GetLeastNumbers() {
        int[] nums = {4, 5, 1, 6, 2, 7, 3, 8};
        int k = 4;
        List<Integer> result = subject40GetLeastNumbers(nums, k);
        System.out.println("the least " + k + " nums: " + result.toString());
    }

    /**
     * refe: https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/solution/er-cha-shu-de-shen-du-by-leetcode-soluti-dk8c/ method2
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param root
     * @return
     */
    public static int subject55MaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> que = new LinkedList<>();
        que.offer(root);
        int depth = 0;
        while (!que.isEmpty()) {
            int curLevelSum = que.size();
            for (int i = 0; i < curLevelSum; i++) {
                TreeNode treeNode = que.poll();
                if (treeNode.left != null) {
                    que.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    que.offer(treeNode.right);
                }
            }
            depth++;
        }
        return depth;
    }

    /**
     * refe: https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/mian-shi-ti-68-i-er-cha-sou-suo-shu-de-zui-jin-g-7/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode subject68ILowestCommonAncestor(TreeNode root, TreeNode p,TreeNode q){
        while (root != null){
            if (root.data < p.data && root.data < q.data){// p、q in right subtree
                root = root.right;
            }else if(root.data > p.data && root.data > q.data){// p、q in left subtree
                root = root.left;
            }else {// find lowest common ancestor
                break;
            }
        }
        return root;
    }

    /**
     * refe: https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/mian-shi-ti-68-ii-er-cha-shu-de-zui-jin-gong-gon-7/
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode subject68LowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }

        TreeNode leftLowestCommon = subject68LowestCommonAncestor(root.left, p, q);
        TreeNode rightLowestCommon = subject68LowestCommonAncestor(root.right, p, q);
        return leftLowestCommon == null ? rightLowestCommon : (rightLowestCommon == null ? leftLowestCommon : root);
    }

    /**
     * refe: https://leetcode.cn/problems/invert-binary-tree/solution/fan-zhuan-er-cha-shu-by-leetcode-solution/
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param root
     * @tparam T
     */
    public static TreeNode revertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = revertTree(root.right);
        root.right = revertTree(root.left);
        return root;
    }

    /**
     * refe: https://leetcode.cn/problems/maximum-depth-of-binary-tree/solution/er-cha-shu-de-zui-da-shen-du-by-leetcode-solution/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param root
     * @return
     * @tparam T
     */
    public static int treeMaxDepthByRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(treeMaxDepthByRecursion(root.left), treeMaxDepthByRecursion(root.right)) + 1;
    }

    /**
     * refe: https://leetcode.cn/problems/maximum-depth-of-binary-tree/solution/er-cha-shu-de-zui-da-shen-du-by-leetcode-solution/ method2
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param root
     * @return
     * @tparam T
     */
    public static int treeMaxDepthIterator(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Deque<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int curLevelSize = que.size();
            depth++;
            for (int i = 0; i < curLevelSize; i++) {
                TreeNode treeNode = que.poll();
                //noinspection ConstantConditions
                if (treeNode.left != null) {
                    que.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    que.offer(treeNode.right);
                }
            }
        }
        return depth;
    }

    /**
     * refe: https://leetcode.cn/problems/validate-binary-search-tree/solution/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/ method1
     * Time complexity: O(n)
     * Spatial complexity:O(n)
     *
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isValidBST(TreeNode root, int min, int max) {
        if (root == null) return true;
        if (root.data <= min || root.data >= max) return false;
        return isValidBST(root.left, min, root.data) && isValidBST(root.right, root.data, max);
    }


    /**
     * refe: https://leetcode.cn/problems/path-sum/solution/lu-jing-zong-he-by-leetcode-solution/
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param root
     * @param sum  sum of path value
     * @return return true if has path or false
     */
    public static boolean hasPath(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        // arrive at the leaf node and compare
        if (root.left == null && root.right == null) {
            return root.data == sum;
        }
        return hasPath(root.left, sum - root.data) || hasPath(root.right, sum - root.data);
    }

    /**
     * refe: https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/solution/dui-cheng-de-er-cha-shu-by-leetcode-solu-rgks/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param root
     * @return
     */
    public static boolean subject28IsSymmetric(TreeNode root) {
        return checkMirror(root, root);
    }

    /**
     * check p tree is mirror of q tree
     *
     * @param p
     * @param q
     * @return
     */
    private static boolean checkMirror(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        // p != null && q != null
        return p.data == q.data && checkMirror(p.left, q.right) && checkMirror(p.right, q.left);
    }

    /**
     * refe: https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/solution/mian-shi-ti-54-er-cha-sou-suo-shu-de-di-k-da-jie-d/
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     */
    public static int k = 0;
    public static int kLargeResult = 0;

    public static int subject54KLarge(TreeNode root, int k) {
        MyTree.k = k;
        reDfsRecursive(root);
        return kLargeResult;
    }

    /**
     * revert dfs for descend
     *
     * @param root
     */
    private static void reDfsRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        // first right sub tree
        reDfsRecursive(root.right);
        // root node
        if (MyTree.k == 0) {
            return;
        }
        if (--MyTree.k == 0) {
            MyTree.kLargeResult = root.data;
            return;
        }
        // last left sub tree
        reDfsRecursive(root.left);
    }

    /**
     * refe: https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/solution/mian-shi-ti-55-ii-ping-heng-er-cha-shu-cong-di-zhi/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param root
     * @return
     */
    public static boolean subject55IIisBalanceTree(TreeNode root) {
        return checkBalanceTree(root) != -1;
    }

    /**
     * check root is balanced tree
     *
     * @param root
     * @return root tree depth or -1 if root tree is not balanced tree
     */
    private static int checkBalanceTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = checkBalanceTree(root.left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = checkBalanceTree(root.right);
        if (rightDepth == -1) {
            return -1;
        }
        return Math.abs(leftDepth - rightDepth) < 2 ? Math.max(rightDepth, leftDepth) + 1 : -1;
    }
}
