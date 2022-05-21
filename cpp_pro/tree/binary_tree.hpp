//
// Created by cainjiang on 2022/5/19.
//
#pragma clang diagnostic push
#pragma ide diagnostic ignored "OCUnusedGlobalDeclarationInspection"
#pragma ide diagnostic ignored "OCUnusedStructInspection"
#ifndef CPP_PRO_BINARY_TREE_HPP
#define CPP_PRO_BINARY_TREE_HPP

#include <vector>
#include <algorithm>
#include <queue>

namespace binary_tree {
    using namespace std;
    template<typename T>
    struct Node {
        T data;
        Node<T> *lChild;
        Node<T> *rChild;
    };

    /**
     * binary tree for search
     */
    class BinarySearchTree {
    private:
        // root Node
        Node<int> *root;
    public:
        explicit BinarySearchTree(Node<int> *root);

        ~BinarySearchTree();

        /**
         * balance bst
         * Time complexity: O(logn)
         * @param data the data to find
         * @return return Node found
         */
        Node<int> *find(int data);

        /**
         * balance bst
         * Time complexity: O(logn)
         * insert data to binary tree
         * @param data the data to be inserted
         */
        void insert(int data);

        /**
         * balance bst
         * Time complexity: O(logn)
         * delete node from binary tree
         * @param data the data to be deleted
         */
        void deleteData(int data);
    };

    class CommonBinaryTree {
    private:
        Node<int> *root;
    public:
        /**
         * refe: https://leetcode.cn/problems/binary-tree-preorder-traversal/ method1
         * pre oder visit by recursion
         * Time complexity: O(n)
         * Spatial complexity: O(n)
         */
        static void preByRecursion(Node<int> *node);

        /**
         * refe: https://leetcode.cn/problems/binary-tree-preorder-traversal/ method2
         * pre oder visit by iteration
         * Time complexity: O(n)
         * Spatial complexity: O(n)
         */
        static void preByIteration(Node<int> *node);

        /**
         * refe: https://leetcode.cn/problems/binary-tree-inorder-traversal/ method1
         * mid oder visit by recursion
         * Time complexity: O(n)
         * Spatial complexity: O(n)
         */
        static void midByRecursion(Node<int> *node);

        /**
         * refe: https://leetcode.cn/problems/binary-tree-inorder-traversal/ method2
         * mid oder visit by iteration
         * Time complexity: O(n)
         * Spatial complexity: O(n)
         */
        static void midByIteration(Node<int> *node);

        /**
         * refe: https://leetcode.cn/problems/binary-tree-postorder-traversal/ method1
         * post oder visit by recursion
         * Time complexity: O(n)
         * Spatial complexity: O(n)
         */
        static void postByRecursion(Node<int> *node);

        /**
         * refe: https://leetcode.cn/problems/binary-tree-postorder-traversal/ method2
         * post oder visit by iteration
         * Time complexity: O(n)
         * Spatial complexity: O(n)
         */
        static void postIteration(Node<int> *node);

        /**
         * refe: https://leetcode.cn/problems/binary-tree-level-order-traversal/solution/er-cha-shu-de-ceng-xu-bian-li-by-leetcode-solution/
         * Time complexity: O(n)
         * Spatial complexity: O(n)
         * @param root
         * @return
         */
        static vector<vector<Node<int> *>> leveOrderVisit(const Node<int> *root);
        static void test_level_order_visit();

        /**
         * refe: https://leetcode.cn/problems/invert-binary-tree/solution/fan-zhuan-er-cha-shu-by-leetcode-solution/
         * Time complexity: O(n)
         * Spatial complexity: O(n)
         * @tparam T
         * @param root
         */
        template<typename T>
        static Node<T> *revertTreeByRecursion(Node<T> *root);

        /**
         * refe: https://leetcode.cn/problems/maximum-depth-of-binary-tree/solution/er-cha-shu-de-zui-da-shen-du-by-leetcode-solution/ method1
         * Time complexity: O(n)
         * Spatial complexity: O(height)
         * @tparam T
         * @param root
         * @return
         */
        template<typename T>
        static int maxDepthByRecursion(Node<T> *root);

        /**
         * refe: https://leetcode.cn/problems/maximum-depth-of-binary-tree/solution/er-cha-shu-de-zui-da-shen-du-by-leetcode-solution/ method2
         * Time complexity: O(n)
         * Spatial complexity: O(n)
         * @tparam T
         * @param root
         * @return
         */
        template<typename T>
        static int maxDepthByIteration(Node<T> *root);

        /**
         * refe: https://leetcode.cn/problems/validate-binary-search-tree/solution/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/ method1
         * Time complexity: O(n)
         * Spatial complexity:O(height)
         * @param root
         * @return
         */
        static bool isValidBST(Node<int> *root);
        /**
         * help to check root tree is bst
         * @param root root node
         * @param min min num of tree
         * @param max max num of tree
         * @return return true if root tree is bst or false
         */
        static bool isValidBstHelper(Node<int> *root,int min,int max);
        static void text_is_valid_bst();

        /**
         * refe: https://leetcode.cn/problems/path-sum/solution/lu-jing-zong-he-by-leetcode-solution/
         * Time complexity: O(n)
         * Spatial complexity: O(height)
         * @param root
         * @param sum sum of path value
         * @return return true if has path or false
         */
        static bool hasPathSum(Node<int> *root, int sum);
        static void test_has_path_sum();
    };

    template<typename T>
    Node<T> *CommonBinaryTree::revertTreeByRecursion(Node<T> *root) {
        if (root == nullptr) {
            return nullptr;
        }

        Node<T> *left = revertTreeByRecursion(root->lChild);
        Node<T> *right = revertTreeByRecursion(root->rChild);
        root->lChild = right;
        root->rChild = left;
        return root;
    }

    template<typename T>
    int CommonBinaryTree::maxDepthByRecursion(Node<T> *root) {
        if (root == nullptr) return 0;
        return max(maxDepthByRecursion(root->lChild), maxDepthByRecursion(root->rChild)) + 1;
    }

    template<typename T>
    int CommonBinaryTree::maxDepthByIteration(Node<T> *root) {
        if (root == nullptr) return 0;
        int depth = 0;
        queue<T> qu;
        qu.push(root);
        while (!qu.empty()) {
            int curLevelSize = qu.size();
            if (curLevelSize > 0) {
                depth++;
            }
            for (int i = 0; i < curLevelSize; ++i) {
                Node<T> *node = qu.top();
                qu.pop();
                if (node->lChild != nullptr) {
                    qu.push(node->lChild);
                }
                if (node->rChild != nullptr) {
                    qu.push(node->rChild);
                }
            }
        }
        return depth;
    }
}
#endif //CPP_PRO_BINARY_TREE_HPP

#pragma clang diagnostic pop