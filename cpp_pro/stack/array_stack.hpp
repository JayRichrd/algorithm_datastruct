//
// Created by cainjiang on 2022/5/9.
//

#ifndef CPP_PRO_ARRAY_STACK_HPP
#define CPP_PRO_ARRAY_STACK_HPP

#include "../linked_list/linked_list.hpp"

namespace stack_practice {
    /**
     * stack by array
     * @tparam T
     */
    template<typename T>
    class ArrayStack {
    private:
        // element count
        int count;
        // array capacity
        int n;
        // the pointer of array
        T *items;
    public:
        explicit ArrayStack(int n);

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
    ArrayStack<T>::ArrayStack(int n) {
        this->n = n;
        this->count = 0;
        items = new T[n];
    }

    template<typename T>
    ArrayStack<T>::~ArrayStack() {
        this->count = 0;
        delete[] items;
    }

    template<typename T>
    bool ArrayStack<T>::push(T item) {
        if (count == n) {
            return false;
        }
        items[count] = item;
        count++;
        return true;
    }

    template<typename T>
    T ArrayStack<T>::pop() {
        if (count == 0) {
            return nullptr;
        }
        T item = items[count - 1];
        count--;
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

}
#endif //CPP_PRO_ARRAY_STACK_HPP
