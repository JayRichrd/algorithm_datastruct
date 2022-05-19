//
// Created by cainjiang on 2022/5/19.
//
#pragma clang diagnostic push
#pragma ide diagnostic ignored "OCUnusedGlobalDeclarationInspection"
#pragma ide diagnostic ignored "OCUnusedStructInspection"
#ifndef CPP_PRO_BINARY_TREE_HPP
#define CPP_PRO_BINARY_TREE_HPP

#include <vector>

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
    };
}
#endif //CPP_PRO_BINARY_TREE_HPP

#pragma clang diagnostic pop