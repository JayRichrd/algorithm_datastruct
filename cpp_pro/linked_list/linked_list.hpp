//
// Created by cainjiang on 2022/4/27.
//

#ifndef CPP_PRO_LINKED_LIST_HPP
#define CPP_PRO_LINKED_LIST_HPP

#include <iostream>
#include <vector>
namespace linked_list {
    using namespace std;
    /**
     * linked list Node
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
         * Time complexity: O(size)
         * Spatial complexity: O(1)
         *
         * @param head the pointer of head Node
         * @return the pointer of head Node of reversed linked list
         */
        template<typename T>
        static node<T> *reverse_iterate(node<T> *head);
        static void test_reverse_iterate();

        /**
         * refe: https://leetcode-cn.com/problems/merge-two-sorted-lists/
         * merge sorted linked list
         * Time complexity: O(m+size)
         * Spatial complexity: O(1)
         *
         * @param linked_list_1 ascending linked list
         * @param linked_list_2 ascending linked list
         * @return the head pointer of combine linked list
         */
        static node<int> *merge_sorted_linked_list(node<int> *linked_list_1, node<int> *linked_list_2);
        static void test_merge_sorted_linked_list();

        /**
         * refe: https://leetcode-cn.com/problems/middle-of-the-linked-list/ method3
         * ues two slow pointer and fast pointer to find middle Node of liked list
         * Time complexity: O(size)
         * Spatial complexity: O(1)
         *
         * @param head the head pointer of linked list
         * @return the middle Node's pointer
         */
        template<typename T>
        static node<T> *find_mid_node(node<T> *head);
        static void test_find_mid_node();

        /**
        * refe: https://leetcode-cn.com/problems/linked-list-cycle/solution/huan-xing-lian-biao-by-leetcode-solution/ method2
        * Time complexity: O(size)
        * Spatial complexity: O(1)
        * @tparam T
        * @param head linked list head Node
        * @return 1: has circle, 0: hasn't circle
        */
        template<typename T>
        static bool has_circle(node<T> *head);
        static void test_has_circle();

        /**
         * refe: https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/ method1
         * relay on merge_sorted_linked_list function,
         * max num of every sorted linked list: size
         * size of lists: k
         * Time complexity: O(k^2*size)
         * Spatial complexity: O(1)
         * @param lists vector of sorted linked list
         * @return head Node of merged sorted linked list
         */
        static node<int> *merge_k_sorted_linked_list_1(const vector<node<int> *>& lists);
        /**
         * refe: https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/ method2
         * relay on merge_sorted_linked_list function,
         * max num of every sorted linked list: size
         * size of lists: k
         * Time complexity: O(k*logk*size)
         * Spatial complexity: O(logk)
         * @param lists vector of sorted linked list
         * @return head Node of merged sorted linked list
         */
        static node<int> *merge_k_sorted_linked_list_2(const vector<node<int> *>& lists);
        static void test_merge_k_sorted_linked_list();
    };

    template<typename T>
    node<T> *LinkedList::reverse_iterate(node<T> *head) {
        // the previous Node of the current
        node<T> *pre = nullptr;
        // the current Node
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

    template<typename T>
    node<T> *LinkedList::find_mid_node(node<T> *head) {
        // one step every time
        node<T> *p_one_step = head;
        // two step every time
        node<T> *p_two_step = head;

        while (p_two_step != nullptr && p_two_step->next != nullptr) {
            p_one_step = p_one_step->next;
            p_two_step = p_two_step->next->next;
        }

        return p_one_step;
    }

    template<typename T>
    bool LinkedList::has_circle(node<T> *head) {
        if (head == nullptr || head->next == nullptr) {
            return false;
        }
        node<T> *slow = head, *fast = head->next;
        while (slow != fast) {
            if (fast == nullptr || fast->next == nullptr) {
                return false;
            }
            slow = slow->next;
            fast = fast->next;
            /**
             * compare every move
             */
            if (fast != nullptr && slow == fast) {
                return true;
            }
            fast = fast->next;
        }
        return true;
    }
}

#endif //CPP_PRO_LINKED_LIST_HPP
