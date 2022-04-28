#include <iostream>
#include "override_operator_assign/my_string.hpp"
#include "linked_list/linked_list.hpp"
#include "array/array.hpp"

int main() {
    using namespace std;
    cout <<"=========================================================================================" << endl;
    CMyString::test();

    cout << endl << "=========================================================================================" << endl;
    linked_list::LinkedList::test_reverse();

    cout << endl << "=========================================================================================" << endl;
    array_practice::ArrayPractice::testMergeSortedArray();

    return 0;
}
