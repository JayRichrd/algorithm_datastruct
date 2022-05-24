//
// Created by cainjiang on 2022/5/24.
//
#pragma clang diagnostic push
#pragma ide diagnostic ignored "cppcoreguidelines-narrowing-conversions"

#include "my_string.hpp"
#include <iostream>
#include <stack>

namespace string_practice {
    using namespace std;

    int MyString::find_sub_str_bf(std::string source, std::string target) {
        if (source.size() < target.size()) {
            return -1;
        }
        int source_size = source.size();
        int target_size = target.size();
        for (int i = 0; i < source_size; ++i) {
            bool unmatched = false;
            int j = 0;
            for (; j < target_size; ++j, i++) {
                if (target[j] != source[i]) {
                    unmatched = true;
                    break;
                }
            }
            if (!unmatched) {
                return i - target_size;
            }
        }
        return -1;
    }

    void MyString::test_find_sub_str_bf() {
        string source = "aaaabbbcccabc";
        string target = "abc";
        cout << target << " index: " << find_sub_str_bf(source, target) << endl;
    }

    void MyString::revert_string(vector<char> &str) {
        int size = str.size();
        for (int i = 0, j = size - 1; i < j; ++i, j--) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
    }

    void MyString::test_revert_string() {
        vector<char> str = {'h', 'e', 'l', 'l', 'o'};
        cout << "source str: ";
        for (char c: str) {
            cout << c;
        }
        cout << endl;

        revert_string(str);
        cout << "revert str: ";
        for (char c: str) {
            cout << c;
        }
        cout << endl;
    }

    string MyString::revert_words(string str) {
        int begin = 0, end = str.size() - 1;
        //trim font and back
        while (begin <= end && str[begin] == ' ') begin++;
        while (begin <= end && str[end] == ' ') end--;

        stack<string> stk;
        string word;
        while (begin <= end) {
            char c = str[begin];
            if (c == ' ' && !word.empty()) {
                stk.push(move(word));
                word = "";
            } else if (c != ' ') {
                word += c;
            }
            begin++;
        }
        // be careful, do not forget the last word
        stk.push(move(word));

        string result;
        while (!stk.empty()) {
            result += stk.top();
            stk.pop();
            if (!stk.empty())
                result += " ";
        }
        return result;
    }

    void MyString::test_revert_words() {
        string str = "  leetcode is fun ";
        cout << "str:" << str << endl;
        cout << "revert str:" << revert_words(str) << endl;
    }
}

#pragma clang diagnostic pop