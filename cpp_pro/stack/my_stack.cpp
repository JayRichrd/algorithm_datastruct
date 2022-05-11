//
// Created by cainjiang on 2022/5/11.
//
#include "my_stack.hpp"
#include <stack>
#include <unordered_map>

namespace stack_practice {
    using namespace std;

    bool MyStack::brackets_is_valid(string &str) {
        if (str.size() % 2 == 1 || str.empty()) {
            return false;
        }
        unordered_map<char, char> pairs = {{')', '('},
                                           {']', '['},
                                           {'}', '{'}};
        stack<char> stk;
        for (char letter: str) {
            if (pairs.count(letter)) {// check right bracket
                if (stk.empty() || stk.top() != pairs[letter]) {
                    return false;
                }
                stk.pop();
            } else {
                stk.push(letter);
            }
        }
        return stk.empty();
    }

    void MyStack::test_brackets_is_valid() {
        string str1 = "([{}])";
        cout << "source string: " << str1 << endl;
        cout << "is bracket valid: " << brackets_is_valid(str1) << endl;
        string str2 = "(([{}])";
        cout << "source string: " << str2 << endl;
        cout << "is bracket valid: " << brackets_is_valid(str2) << endl;
    }
}