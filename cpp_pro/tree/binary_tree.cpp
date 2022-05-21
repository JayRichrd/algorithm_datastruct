//
// Created by cainjiang on 2022/5/19.
//
#pragma ide diagnostic ignored "cppcoreguidelines-narrowing-conversions"
#pragma clang diagnostic push
#pragma ide diagnostic ignored "LocalValueEscapesScope"
#pragma ide diagnostic ignored "misc-no-recursion"
#pragma ide diagnostic ignored "OCUnusedGlobalDeclarationInspection"

#include "binary_tree.hpp"
#include <iostream>
#include <stack>
#include <queue>
#include <cstdint>

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

    BinarySearchTree::~BinarySearchTree() = default;

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

    vector<vector<Node<int> *>> CommonBinaryTree::leveOrderVisit(const Node<int> *root) {
        vector<vector<Node<int> *>> result;
        if (root == nullptr)
            return result;

        queue<Node<int> *> qu;
        auto *cur = const_cast<Node<int> *>(root);
        qu.push(cur);
        while (!qu.empty()) {
            int curLeveSize = qu.size();
            vector<Node<int> *> curLeveVector;
            for (int i = 0; i < curLeveSize; ++i) {
                Node<int> *node = qu.front();
                qu.pop();
                curLeveVector.push_back(node);
                //push next level node into queue
                if (node->lChild != nullptr) {
                    qu.push(node->lChild);
                }
                if (node->rChild != nullptr) {
                    qu.push(node->rChild);
                }
            }
            result.push_back(curLeveVector);
        }
        return result;
    }

    void CommonBinaryTree::test_level_order_visit() {
        Node<int> node5 = Node<int>{7, nullptr, nullptr};
        Node<int> node4 = Node<int>{15, nullptr, nullptr};
        Node<int> node3 = Node<int>{20, &node4, &node5};
        Node<int> node2 = Node<int>{9, nullptr, nullptr};
        Node<int> node1 = Node<int>{3, &node2, &node3};
        vector<vector<Node<int> *>> levelOrderVisitResult = leveOrderVisit(&node1);
        for (int i = 0; i < levelOrderVisitResult.size(); ++i) {
            cout << "level " << i + 1 << ": ";
            vector<Node<int> *> curLevelVector = levelOrderVisitResult[i];
            for (auto &node: curLevelVector) {
                cout << node->data << ", ";
            }
            cout << endl;
        }
    }

    bool CommonBinaryTree::isValidBST(Node<int> *root) {
        return isValidBstHelper(root, INTMAX_MIN, INTMAX_MAX);
    }

    bool CommonBinaryTree::isValidBstHelper(Node<int> *root, int min, int max) {
        if (root == nullptr) return true;
        if (root->data <= min || root->data >= max) {
            return false;
        }
        return isValidBstHelper(root->lChild, min, root->data) && isValidBstHelper(root->rChild, root->data, max);
    }

    void CommonBinaryTree::text_is_valid_bst() {
        Node<int> node5 = {6, nullptr, nullptr};
        Node<int> node4 = {3, nullptr, nullptr};
        Node<int> node3 = {4, &node4, &node5};
        Node<int> node2 = {1, nullptr, nullptr};
        Node<int> node1 = {5, &node2, &node3};
        cout << "is valid bst: " << isValidBST(&node1);
    }

    bool CommonBinaryTree::hasPathSum(Node<int> *root, int sum) {
        if (root == nullptr) {
            return false;
        }
        // arrive at leaf node
        if (root->lChild == nullptr && root->rChild == nullptr) {
            return sum == root->data;
        }
        return hasPathSum(root->lChild, sum - root->data) || hasPathSum(root->rChild, sum - root->data);
    }

    void CommonBinaryTree::test_has_path_sum() {
        Node<int> node9 = {1, nullptr, nullptr};
        Node<int> node8 = {2, nullptr, nullptr};
        Node<int> node7 = {7, nullptr, nullptr};
        Node<int> node6 = {4, nullptr, &node9};
        Node<int> node5 = {13, nullptr, nullptr};
        Node<int> node4 = {11, &node7, &node8};
        Node<int> node3 = {8, &node5, &node6};
        Node<int> node2 = {4, &node4, nullptr};
        Node<int> node1 = {5, &node2, &node3};
        int sum = 22;
        cout << "has path = " << sum << " : " << hasPathSum(&node1, sum);
    }
}

#pragma clang diagnostic pop