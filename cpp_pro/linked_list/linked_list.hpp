//
// Created by cainjiang on 2022/4/27.
//

#ifndef CPP_PRO_LINKED_LIST_HPP
#define CPP_PRO_LINKED_LIST_HPP
namespace linked_list {
    /**
     * linked list node
     * @tparam T
     */
    template<typename T>
    struct node {
        T data;
        node<T> *next;
    };

    class LinkedList {
    public:
        /**
         * refe: https://leetcode-cn.com/problems/reverse-linked-list/
         * reverse linked list by iterate
         * before: 1 -> 2 -> 3 -> 4 -> null
         * after: null <- 1 <- 2 <- 3 <- 4
         * Time complexity: O(n)
         * Spatial complexity: O(1)
         *
         * @param head the pointer of head node
         * @return the pointer of head node of reversed linked list
         */
        template<typename T>
        static node<T> *reverse_iterate(node<T> *head);
        static void test_reverse_iterate();

        /**
         * refe: https://leetcode-cn.com/problems/merge-two-sorted-lists/
         * merge sorted linked list
         * Time complexity: O(m+n)
         * Spatial complexity: O(1)
         * @param linked_list_1 ascending linked list
         * @param linked_list_2 ascending linked list
         * @return the head pointer of combine linked list
         */
        static node<int> *merge_sorted_linked_list(node<int> *linked_list_1, node<int> *linked_list_2);
        static void test_merge_sorted_linked_list();
    };


    template<typename T>
    node<T> *LinkedList::reverse_iterate(node<T> *head) {
        // the previous node of the current
        node<T> *pre = nullptr;
        // the current node
        node<T> *cur = head;
        while (cur != nullptr) {
            /**
             * save the next of current before operate
             */
            node<T> *temp = cur->next;
            /**
             * let the next pointer of the current point to the previous
             * and 'pre' and 'cur' move one position back
             */
            cur->next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}

#endif //CPP_PRO_LINKED_LIST_HPP
