//
// Created by cainjiang on 2022/5/9.
//

#ifndef CPP_PRO_MY_STACK_HPP
#define CPP_PRO_MY_STACK_HPP

#include "../linked_list/linked_list.hpp"
#include <string>

namespace stack_practice {
    using namespace std;

    /**
     * stack by array
     * @tparam T
     */
    template<typename T>
    class ArrayStack {
    private:
        // element top
        int top;
        // array capacity
        int size;
        // the pointer of array
        T *items;
    public:
        explicit ArrayStack(int size);

        ~ArrayStack();

        /**
         * push element
         * @param item the element which to be pushed into stack top
         * @return push success return true or false
         */
        bool push(T item);

        /**
         * get stack top element
         * @return
         */
        T pop();
    };

    template<typename T>
    ArrayStack<T>::ArrayStack(int size) {
        this->size = size;
        this->top = 0;
        items = new T[size];
    }

    template<typename T>
    ArrayStack<T>::~ArrayStack() {
        this->top = 0;
        delete[] items;
    }

    template<typename T>
    bool ArrayStack<T>::push(T item) {
        if (top == size) {
            return false;
        }
        items[top] = item;
        top++;
        return true;
    }

    template<typename T>
    T ArrayStack<T>::pop() {
        if (top == 0) {
            return nullptr;
        }
        T item = items[top - 1];
        top--;
        return item;
    }

    //----------------------------------------linked list stack--------------------------------------------------------
    /**
     * stack by linked list
     * @tparam T
     */
    template<typename T>
    class LinkedListStack {
    private:
        // stack top
        struct linked_list::node<T> *top = nullptr;
    public:
        /**
         * push element
         * @param item the element which to be pushed into stack top
         * @return push success return true or false
         */
        bool push(linked_list::node<T> *item);

        /**
         * get stack top element
         * @return
         */
        linked_list::node<T> *pop();
    };

    template<typename T>
    bool LinkedListStack<T>::push(linked_list::node<T> *item) {
        item->next = top;
        top = item;
        return true;
    }

    template<typename T>
    linked_list::node<T> *LinkedListStack<T>::pop() {
        if (top == nullptr) {
            return nullptr;
        }
        linked_list::node<T> *item = top;
        top = top->next;
        item->next = nullptr;
        return item;
    }

    class MyStack {
    public:
        /**
         * refe: https://leetcode.cn/problems/valid-parentheses/solution/you-xiao-de-gua-hao-by-leetcode-solution/
         * Time complexity: O(n)
         * Spatial complexity: O(n)
         * @param str: source string
         * @return return true if the source string bracket valid or false
         */
        static bool brackets_is_valid(string &str);
        static void test_brackets_is_valid();

        /**
         * refe: https://leetcode.cn/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/ method2
         * Time complexity: O(n)
         * Spatial complexity: O(n)
         * @param str source string
         * @return the max num of valid parentheses
         */
        static int longest_valid_parentheses_2(string &str);
        /**
         * refe: https://leetcode.cn/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/ method3
         * Time complexity: O(n)
         * Spatial complexity: O(1)
         * @param str source string
         * @return the max num of valid parentheses
         */
        static int longest_valid_parentheses_3(string &str);
        static void test_longest_valid_parentheses();

        static int arithmetic(string &str);
        static void test_arithmetic();
    };
}
#endif //CPP_PRO_MY_STACK_HPP
