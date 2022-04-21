//
// Created by cainjiang on 2022/4/20.
//

#include "my_string.hpp"
#include <iostream>

CMyString::CMyString(char *data) {
    if (data == nullptr) {
        p_mData = new char[1];
        p_mData[0] = '\0';
        return;
    }
    p_mData = new char[strlen(data) + 1];
    strcpy(p_mData, data);
}

CMyString::CMyString(const CMyString &cMyString) {
    if (cMyString.p_mData == nullptr) {
        p_mData = new char[1];
        p_mData[0] = '\0';
        return;
    }
    p_mData = new char[strlen(cMyString.p_mData) + 1];
    strcpy(p_mData, cMyString.p_mData);
}

CMyString::~CMyString(void) {
    std::cout << this << ", data: " << p_mData << ", delete this instance." << std::endl;
    delete[] p_mData;
}

CMyString &CMyString::operator=(const CMyString &other) {
    std::cout << "call override assign function. other(" << &other << ") data: " << other.p_mData << ", this("
              << this << ") data: "
              << this->p_mData << std::endl;
    /**
     * if the same instance, just return it.
     */
    if (this != &other) {
        CMyString temp_cMyString(other);
        char *temp_pData = temp_cMyString.p_mData;
        temp_cMyString.p_mData = p_mData;
        p_mData = temp_pData;
    }
    return *this;
}

char *CMyString::getData() {
    return p_mData;
}
