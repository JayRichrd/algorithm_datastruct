//
// Created by cainjiang on 2022/5/23.
//
#pragma clang diagnostic push
#pragma ide diagnostic ignored "OCUnusedGlobalDeclarationInspection"
#ifndef CPP_PRO_MY_SORT_HPP
#define CPP_PRO_MY_SORT_HPP

#include <vector>

namespace sort_practice {
    using namespace std;

    class MySort {
    private:
        /**
         * merge sort recursion
         * @param nums
         * @param start
         * @param end
         */
        static void merge_sort_recursion(vector<int> &nums, int start, int end);

        /**
         * quick sort recursion
         * @param nums
         * @param left
         * @param right
         */
        static void quick_sort_recursion(vector<int> &nums, int left, int right);

        /**
         * swap element
         * @param nums
         * @param i
         * @param j
         */
        static void quick_sort_swap(vector<int> &nums, int i, int j);

        /**
         * partition function for quick sort
         * @param nums
         * @param left
         * @param right
         * @return return pivot index
         */
        static int quick_sort_partition(vector<int> &nums, int left, int right);

    public:
        /**
         * Time complexity: O(n^2)
         * Spatial complexity: O(1)
         * Stable sort
         * @param nums
         */
        static void bubble_sort(vector<int> &nums);

        /**
         * Time complexity: O(n^2)
         * Spatial complexity: O(1)
         * Stable sort
         * @param nums
         */
        static void insert_sort(vector<int> &nums);

        /**
         * refe: http://www.leetcodecn.com/pages/c8ac2e/#_1-%E7%AE%97%E6%B3%95%E6%AD%A5%E9%AA%A4
         * Time complexity: O(n^2)
         * Spatial complexity: O(1)
         * Unstable sort
         * @param nums
         */
        static void select_sort(vector<int> &nums);

        /**
         * refe: https://leetcode.cn/problems/sort-an-array/solution/pai-xu-shu-zu-by-leetcode-solution/ method3
         * Time complexity: O(n*logn)
         * Spatial complexity: O(n)
         * Stable sort
         * @param nums
         */
        static void merge_sort(vector<int> &nums);

        /**
         * refe: https://leetcode.cn/problems/sort-an-array/solution/pai-xu-shu-zu-by-leetcode-solution/ method1
         * Time complexity: O(n*logn)
         * Spatial complexity: O(1)
         * Unstable sort
         * @param nums
         */
        static void quick_sort(vector<int> &nums);
        static void test_quick_sort();
    };
}
#endif //CPP_PRO_MY_SORT_HPP

#pragma clang diagnostic pop