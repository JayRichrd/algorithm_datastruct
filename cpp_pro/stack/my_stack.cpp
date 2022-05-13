//
// Created by cainjiang on 2022/5/11.
//
#include "my_stack.hpp"
#include <stack>
#include <unordered_map>
#include <algorithm>

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

    int MyStack::longest_valid_parentheses_2(string &str) {
        int max_ans = 0;
        stack<int> stk;
        stk.push(-1);
        for (int i = 0; i < str.length(); ++i) {
            if (str[i] == '(') {
                stk.push(i);
            } else {
                /**
                 * when right bracket
                 * pop top element firstly, and then check and compute max_ans.
                 */
                stk.pop();
                if (stk.empty()) {
                    stk.push(i);
                } else {
                    max_ans = max(max_ans, i - stk.top());
                }
            }
        }
        return max_ans;
    }

    void MyStack::test_longest_valid_parentheses() {
        string str1 = "())((())";
        cout << "str1: " << str1 << endl;
        cout << "max num of valid parentheses: " << longest_valid_parentheses_2(str1) << endl;
        string str2 = "()((())";
        cout << "str2: " << str2 << endl;
        cout << "max num of valid parentheses: " << longest_valid_parentheses_3(str2) << endl;
    }

    int MyStack::longest_valid_parentheses_3(string &str) {
        int max_ans = 0, left = 0, right = 0;
        /**
         * left -> right
         */
        for (char i: str) {
            if (i == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max_ans = max(max_ans, right * 2);
            } else if (right > left) {
                left = right = 0;
            }
        }

        left = right = 0;
        /**
         * right -> left
         */
        for (int i = str.length() - 1; i >= 0; --i) {
            if (str[i] == ')') {
                right++;
            } else {
                left++;
            }
            if (right == left) {
                max_ans = max(max_ans, left * 2);
            } else if (left > right) {
                right = left = 0;
            }
        }

        return max_ans;
    }

    bool isNum(const char &letter) {
        if (letter >= '0' && letter <= '9') {
            return true;
        } else {
            return false;
        }
    }

    int MyStack::arithmetic(string &str) {
        string reverse_polish_notatio_str;
        stack<char> symbol_stk;
        for (char letter: str) {

        }
        return 0;
    }


    void MyStack::test_arithmetic() {

    }


}