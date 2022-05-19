//
// Created by cainjiang on 2022/5/19.
//
#include "binary_tree.hpp"

namespace binary_tree {

    Node<int> *BinarySearchTree::find(int data) {
        Node<int> *curNode = root;
        while (curNode != nullptr) {
            if (data < curNode->data) {
                curNode = curNode->lChild;
            } else if (data == curNode->data) {
                return curNode;
            } else {
                curNode = curNode->rChild;
            }
        }
        return nullptr;
    }

    BinarySearchTree::BinarySearchTree(Node<int> *root) {
        this->root = root;
    }

    BinarySearchTree::~BinarySearchTree() {

    }

    void BinarySearchTree::insert(int data) {
        if (root == nullptr) {
            root = new Node<int>{data, nullptr, nullptr};
            return;
        }
        Node<int> *curNode = root;
        while (curNode != nullptr) {
            if (data < curNode->data) {
                if (curNode->lChild == nullptr) {
                    curNode->lChild = new Node<int>{data, nullptr, nullptr};
                    return;
                }
                curNode = curNode->lChild;
            } else {
                if (curNode->rChild == nullptr) {
                    curNode->rChild = new Node<int>{data, nullptr, nullptr};
                    return;
                }
                curNode = curNode->rChild;
            }
        }
    }

    void BinarySearchTree::deleteData(int data) {
        Node<int> *curNode = root;
        Node<int> *previousNode = nullptr;
        // find node firstly
        while (curNode != nullptr && curNode->data != data) {
            previousNode = curNode;
            if (data < curNode->lChild->data) {
                curNode = curNode->lChild;
            } else {
                curNode = curNode->rChild;
            }
        }

        if (curNode == nullptr) {
            return;
        }

        if (curNode->lChild != nullptr && curNode->rChild != nullptr) {
            Node<int> *minParent = curNode;
            Node<int> *min = curNode->rChild;
            // find right subtree min node
            while (min->lChild != nullptr) {
                minParent = min;
                min = min->lChild;
            }
            // use the right subtree min node data replace data of the node to be deleted
            curNode->data = min->data;
            // ready to delete right subtree min node which hasn't left subtree
            curNode = min;
            previousNode = minParent;
        }

        // the node to be placed to the parent's child node
        Node<int> *child;
        if (curNode->lChild != nullptr) {
            child = curNode->lChild;
        } else if (curNode->rChild != nullptr) {
            child = curNode->rChild;
        } else {
            child = nullptr;
        }

        // replace parent's child node
        if (previousNode == nullptr) {// be careful
            this->root = child;
        } else if (previousNode->lChild->data == curNode->data) {
            previousNode->lChild = child;
        } else {
            previousNode->rChild = child;
        }
    }
}
