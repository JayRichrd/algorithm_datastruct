#include <iostream>
#include "override_operator_assign/my_string.hpp"
#include "linked_list/linked_list.hpp"
#include "array/array.hpp"
#include "recursion/my_recursion.hpp"

int main() {
    using namespace std;
    cout << "===============================test override assign operator=====================================" << endl;
    CMyString::test_override_assign_operator();

    cout << endl << "============================test reverse iterate=========================================" << endl;
    linked_list::LinkedList::test_reverse_iterate();

    cout << endl << "=============================test merge sorted array====================================" << endl;
    array_practice::ArrayPractice::test_merge_sorted_array();

    cout << endl << "============================test merge sorted linked list================================" << endl;
    linked_list::LinkedList::test_merge_sorted_linked_list();

    cout << endl << "============================test find mid Node================================" << endl;
    linked_list::LinkedList::test_find_mid_node();

    cout << endl << "============================test has circle================================" << endl;
    linked_list::LinkedList::test_has_circle();

    cout << endl << "=========================test merge k sorted linked list=============================" << endl;
    linked_list::LinkedList::test_merge_k_sorted_linked_list();

    cout << endl << "============================test major element================================" << endl;
    array_practice::ArrayPractice::test_major_element();

    cout << endl << "============================test fibonacci sequence================================" << endl;
    recursive_practice::test_fibonacci_sequence();

    cout << endl << "============================test factorial================================" << endl;
    recursive_practice::test_factorial();

    return 0;
}
