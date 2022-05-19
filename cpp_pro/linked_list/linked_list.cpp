//
// Created by cainjiang on 2022/4/27.
//
#include "linked_list.hpp"

namespace linked_list {
    using namespace std;

    node<int> *LinkedList::merge_sorted_linked_list(node<int> *linked_list_1, node<int> *linked_list_2) {
        node<int> *head, *cur;
        head = cur = new node<int>{0, nullptr};
        while (linked_list_1 != nullptr && linked_list_2 != nullptr) {
            if (linked_list_1->data >= linked_list_2->data) {
                cur->next = linked_list_2;
                cur = cur->next;
                linked_list_2 = linked_list_2->next;
            } else {
                cur->next = linked_list_1;
                cur = cur->next;
                linked_list_1 = linked_list_1->next;
            }
        }

        if (linked_list_1 == nullptr) {
            cur->next = linked_list_2;
        }
        if (linked_list_2 == nullptr) {
            cur->next = linked_list_1;
        }
        return head->next;
    }

    node<int> *LinkedList::merge_k_sorted_linked_list_1(const vector<node<int> *> &lists) {
        node<int> *merge = nullptr;
        for (auto &list: lists) {
            merge = merge_sorted_linked_list(merge, list);
        }
        return merge;
    }

    node<int> *merge_linked_list(const vector<node<int> *> &lists, size_t left, size_t right) {
        if (left == right) return lists[left];
        if (left > right) return nullptr;
        size_t mid = (left + right) >> 1;
        return LinkedList::merge_sorted_linked_list(merge_linked_list(lists, left, mid),
                                                    merge_linked_list(lists, mid + 1, right));
    }

    node<int> *LinkedList::merge_k_sorted_linked_list_2(const vector<node<int> *> &lists) {
        return merge_linked_list(lists, 0, lists.size() - 1);
    }

    void LinkedList::test_reverse_iterate() {
        node<int> node4 = {4, nullptr};
        node<int> node3 = {3, &node4};
        node<int> node2 = {2, &node3};
        node<int> node1 = {1, &node2};
        node<int> *head = &node1;
        cout << "before:" << endl;
        while (head) {
            cout << head->data << ", ";
            head = head->next;
        }

        cout << endl << "-------------------------------------------------------------------------------------" << endl;

        head = reverse_iterate<int>(&node1);
        cout << "after:" << endl;
        while (head) {
            cout << head->data << ", ";
            head = head->next;
        }
    }

    void LinkedList::test_merge_sorted_linked_list() {
        node<int> list1_node4 = {6, nullptr};
        node<int> list1_node3 = {4, &list1_node4};
        node<int> list1_node2 = {3, &list1_node3};
        node<int> list1_node1 = {1, &list1_node2};

        node<int> list2_node3 = {4, nullptr};
        node<int> list2_node2 = {2, &list2_node3};
        node<int> list2_node1 = {1, &list2_node2};

        node<int> *head = merge_sorted_linked_list(&list1_node1, &list2_node1);
        while (head) {
            cout << head->data << ", ";
            head = head->next;
        }
        cout << endl;
    }

    void LinkedList::test_find_mid_node() {
        node<int> list1_node4 = {4, nullptr};
        node<int> list1_node3 = {3, &list1_node4};
        node<int> list1_node2 = {2, &list1_node3};
        node<int> list1_node1 = {1, &list1_node2};
        node<int> *head = &list1_node1;
        cout << "list1:" << endl;
        while (head) {
            cout << head->data << ", ";
            head = head->next;
        }
        node<int> *mid = find_mid_node(&list1_node1);
        cout << endl << "list1 middle Node: " << mid->data << endl;

        cout << endl << "------------------------------------------------------------------------------------" << endl;

        node<int> list2_node6 = {6, nullptr};
        node<int> list2_node5 = {5, &list2_node6};
        node<int> list2_node4 = {4, &list2_node5};
        node<int> list2_node3 = {3, &list2_node4};
        node<int> list2_node2 = {2, &list2_node3};
        node<int> list2_node1 = {1, &list2_node2};
        head = &list2_node1;
        cout << "list2:" << endl;
        while (head) {
            cout << head->data << ", ";
            head = head->next;
        }
        mid = find_mid_node(&list2_node1);
        cout << endl << "list2 middle Node: " << mid->data << endl;
    }

    void LinkedList::test_has_circle() {
        node<int> list1_node4 = {-4, nullptr};
        node<int> list1_node3 = {0, &list1_node4};
        node<int> list1_node2 = {2, &list1_node3};
        node<int> list1_node1 = {3, &list1_node2};
        list1_node4.next = &list1_node2;
        node<int> *head = &list1_node1;
        cout << "list1 has circle: " << has_circle(head) << endl;
        node<int> list2_node6 = {6, nullptr};
        node<int> list2_node5 = {5, &list2_node6};
        node<int> list2_node4 = {4, &list2_node5};
        node<int> list2_node3 = {3, &list2_node4};
        node<int> list2_node2 = {2, &list2_node3};
        node<int> list2_node1 = {1, &list2_node2};
        head = &list2_node1;
        cout << "list2 has circle: " << has_circle(head) << endl;
    }

    void LinkedList::test_merge_k_sorted_linked_list() {
        vector<node<int> *> lists;
        node<int> *head;
        node<int> list1_node3 = {5, nullptr};
        node<int> list1_node2 = {4, &list1_node3};
        node<int> list1_node1 = {1, &list1_node2};
        lists.push_back(&list1_node1);
        head = &list1_node1;
        cout << "list1: ";
        while (head) {
            cout << head->data << ", ";
            head = head->next;
        }
        cout << endl;
        node<int> list2_node3 = {4, nullptr};
        node<int> list2_node2 = {3, &list2_node3};
        node<int> list2_node1 = {1, &list2_node2};
        lists.push_back(&list2_node1);
        head = &list2_node1;
        cout << "list2: ";
        while (head) {
            cout << head->data << ", ";
            head = head->next;
        }
        cout << endl;
        node<int> list3_node2 = {6, nullptr};
        node<int> list3_node1 = {2, &list3_node2};
        lists.push_back(&list3_node1);
        head = &list3_node1;
        cout << "list3: ";
        while (head) {
            cout << head->data << ", ";
            head = head->next;
        }
        cout << endl;
        //Node<int> *merge = merge_k_sorted_linked_list_1(lists);
        node<int> *merge = merge_k_sorted_linked_list_2(lists);
        head = merge;
        cout << "merged list: ";
        while (head) {
            cout << head->data << ", ";
            head = head->next;
        }
        cout << endl;
    }
}

