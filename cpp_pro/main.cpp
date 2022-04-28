#include <iostream>
#include "override_operator_assign/my_string.hpp"
#include "linked_list/linked_list.hpp"
#include "array/array.hpp"

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

    return 0;
}
