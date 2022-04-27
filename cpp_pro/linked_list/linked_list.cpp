//
// Created by cainjiang on 2022/4/27.
//
#include <iostream>
#include "linked_list.hpp"

void linked_list::LinkedList::test_reverse() {
    using namespace std;
    node<int> node4 = {4, nullptr};
    node<int> node3 = {3, &node4};
    node<int> node2 = {2, &node3};
    node<int> node1 = {1, &node2};
    node<int> *head = &node1;
    cout << "before:" << endl;
    while (head) {
        cout << head->data << endl;
        head = head->next;
    }

    cout << "-------------------------------------------------------------------------------------------------" << endl;

    head = reverse_iterate<int>(&node1);
    cout << "after:" << endl;
    while (head) {
        cout << head->data << endl;
        head = head->next;
    }
}