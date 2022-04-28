//
// Created by cainjiang on 2022/4/27.
//
#include <iostream>
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
}

