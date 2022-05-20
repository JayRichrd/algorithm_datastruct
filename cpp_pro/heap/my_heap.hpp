//
// Created by cainjiang on 2022/5/20.
//

#pragma clang diagnostic push
#pragma ide diagnostic ignored "OCUnusedGlobalDeclarationInspection"
#pragma ide diagnostic ignored "OCUnusedStructInspection"
#ifndef CPP_PRO_MY_HEAP_HPP
#define CPP_PRO_MY_HEAP_HPP

#include <vector>

namespace heap_practice {
    using namespace std;

    class MaxHeap {
    private:
        int capacity;
        int count;
        int *nums;


    public:
        explicit MaxHeap(int capacity);

        ~MaxHeap();

        /**
         * insert a element into the heap tree
         * Time complexity: O(logn)
         * @param data
         * @return return true if insert success or false
         */
        bool insert(int data);

        /**
         * remove top element of heap tree
         * Time complexity: O(logn)
         * @return return true if delete success or false
         */
        bool pop();

        static void swap(int nums[], int i, int j);

        /**
         * heaping from up to down
         * @param nums array represents heap tree
         * @param i start index
         * @param count size of nums
         */
        static void heapify(int nums[], int i, int count);

        /**
         * sort by heap tree
         * Time complexity: O(nlogn)
         * Spatial complexity: O(1)
         * @param nums the array to be sorted
         * @param count size of nums
         */
        static void heapSort(int nums[], int count);
        static void test_heap_sort();

        /**
         * refe: https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/solution/zui-xiao-de-kge-shu-by-leetcode-solution/ method2
         * Time complexity: O(nlogk)
         * Spatial complexity: O(k)
         * @param nums
         * @param k
         * @return
         */
        static vector<int> top_k(vector<int> nums, int k);
        static void test_top_k();
    };
}
#endif //CPP_PRO_MY_HEAP_HPP

#pragma clang diagnostic pop