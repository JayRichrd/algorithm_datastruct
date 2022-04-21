#include <iostream>
#include "my_string.hpp"

int main() {
    CMyString cMyString1("111");
    CMyString cMyString2("222");
    CMyString cMyString3("333");
    cMyString1 = cMyString2 = cMyString3;
    std::cout << "cMyString1 data = " << cMyString1.getData() << std::endl;
    std::cout << "cMyString2 data = " << cMyString2.getData() << std::endl;
    std::cout << "cMyString3 data = " << cMyString3.getData() << std::endl;
    return 0;
}
