#include <iostream>
#include "override_operator_assign/my_string.hpp"
#include "linked_list/linked_list.hpp"
#include "array/array.hpp"
#include "recursion/my_recursion.hpp"
#include "stack/my_stack.hpp"
#include "queue/my_queue.hpp"
#include "tree/binary_tree.hpp"
#include "heap/my_heap.hpp"
#include "sort/my_sort.hpp"
#include "search/my_search.hpp"

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
    recursive_practice::MyRecursion::test_fibonacci_sequence();

    cout << endl << "============================test factorial================================" << endl;
    recursive_practice::MyRecursion::test_factorial();

    cout << endl << "============================test brackets is valid================================" << endl;
    stack_practice::MyStack::test_brackets_is_valid();

    cout << endl << "==========================test longest valid parentheses==============================" << endl;
    stack_practice::MyStack::test_longest_valid_parentheses();

    cout << endl << "==========================test max sliding window==============================" << endl;
    queue_practice::MyQueue::test_max_sliding_window();

    cout << endl << "==========================test climb stairs==============================" << endl;
    recursive_practice::MyRecursion::test_climb_stairs();

    cout << endl << "==========================test level order visit==============================" << endl;
    binary_tree::CommonBinaryTree::test_level_order_visit();

    cout << endl << "==========================text is valid bst==============================" << endl;
    binary_tree::CommonBinaryTree::text_is_valid_bst();

    cout << endl << "==========================test has path sum==============================" << endl;
    binary_tree::CommonBinaryTree::test_has_path_sum();

    cout << endl << "==========================test heap sort==============================" << endl;
    heap_practice::MaxHeap::test_heap_sort();

    cout << endl << "==========================test_top_k==============================" << endl;
    heap_practice::MaxHeap::test_top_k();

    cout << endl << "==========================test quick sort==============================" << endl;
    sort_practice::MySort::test_quick_sort();

    cout << endl << "==========================test kth larger==============================" << endl;
    sort_practice::MySort::test_kth_larger();

    cout << endl << "==========================test binary search==============================" << endl;
    search_practice::MySearch::test_binary_search();

    cout << endl << "==========================test search insert==============================" << endl;
    search_practice::MySearch::test_search_insert();

    cout << endl << "==========================test my sqrt==============================" << endl;
    search_practice::MySearch::test_my_sqrt();

    return 0;
}
