//
// Created by cainjiang on 2022/5/19.
//
#include "binary_tree.hpp"
#include <iostream>
#include <stack>
#include <queue>

namespace binary_tree {
    using namespace std;

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

    void CommonBinaryTree::preByRecursion(Node<int> *node) {
        if (node == nullptr) {
            return;
        }
        cout << node->data << ", ";
        preByRecursion(node->lChild);
        preByRecursion(node->rChild);
    }

    void CommonBinaryTree::preByIteration(Node<int> *node) {
        if (node == nullptr) {
            return;
        }

        stack<Node<int> *> stk;
        Node<int> *cur = node;
        while (cur != nullptr || !stk.empty()) {
            while (cur != nullptr) {
                cout << cur->data << ", ";
                // just as recursion push back stack
                stk.push(cur);
                cur = cur->lChild;
            }
            // just as recursion push back stack
            cur = stk.top();
            stk.pop();
            cur = cur->rChild;
        }
    }

    void CommonBinaryTree::midByRecursion(Node<int> *node) {
        if (node == nullptr) {
            return;
        }
        midByRecursion(node->lChild);
        cout << node->data << ", ";
        midByRecursion(node->rChild);
    }

    void CommonBinaryTree::midByIteration(Node<int> *node) {
        if (node == nullptr) {
            return;
        }

        stack<Node<int> *> stk;
        Node<int> *cur = node;
        while (!stk.empty() || cur != nullptr) {
            while (cur != nullptr) {
                stk.push(cur);
                cur = cur->lChild;
            }
            cur = stk.top();
            stk.pop();
            cout << cur->data << ", ";
            cur = cur->rChild;
        }
    }

    void CommonBinaryTree::postByRecursion(Node<int> *node) {
        if (node == nullptr) {
            return;
        }

        postByRecursion(node->lChild);
        postByRecursion(node->rChild);
        cout << node->data << ", ";
    }

    void CommonBinaryTree::postIteration(Node<int> *node) {
        if (node == nullptr) {
            return;
        }

        stack<Node<int> *> stk;
        Node<int> *cur = node;
        // last visit node
        Node<int> *pre = nullptr;
        while (stk.empty() || cur != nullptr) {
            while (cur != nullptr) {
                stk.push(cur);
                cur = cur->lChild;
            }
            cur = stk.top();
            stk.pop();
            if (cur->rChild == nullptr || cur->rChild->data == pre->data) {// visited condition
                cout << cur->data << ", ";
                pre = cur;
                cur = nullptr;
            } else {
                stk.push(cur);
                cur = cur->rChild;
            }
        }
    }

    vector<vector<Node<int> *>> CommonBinaryTree::leveOrderVisit(Node<int> *root) {
        vector<vector<Node<int> *>> result;
        if (root == nullptr)
            return result;

        queue<Node<int> *> qu;
        qu.push(root);
        while (!qu.empty()) {
            int curLeveSize = qu.size();
            vector<Node<int> *> curLeveVector;

        }
        return result;
    }
}
