#include <iostream>
#include "override_operator_assign/my_string.hpp"
#include "linked_list/linked_list.hpp"

int main() {
    using namespace std;
    CMyString::test();

    cout << endl << "=========================================================================================" << endl;
    linked_list::LinkedList::test_reverse();
    return 0;
}
