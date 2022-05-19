//
// Created by cainjiang on 2022/5/19.
//

#ifndef CPP_PRO_BINARY_TREE_HPP
#define CPP_PRO_BINARY_TREE_HPP
namespace binary_tree {
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
}
#endif //CPP_PRO_BINARY_TREE_HPP
