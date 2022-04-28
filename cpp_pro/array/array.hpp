//
// Created by cainjiang on 2022/4/27.
//

#ifndef CPP_PRO_ARRAY_HPP
#define CPP_PRO_ARRAY_HPP
namespace array_practice {
    class ArrayPractice {
    public:
        /**
         * refe:https://leetcode-cn.com/problems/merge-sorted-array/ method3
         * array1‘s size is larger than array2's size, and size of array1 equals array1's valid element plus array2 size
         * Time complexity: O(m+n)
         * Spatial complexity: O(1)
         * @param array1
         * @param n count of array1's valid element
         * @param array2
         * @param m count of array2's valid element
         */
        static void merge_sorted_array(int array1[], int n, const int array2[], int m);
        static void test_merge_sorted_array();
    };

}
#endif //CPP_PRO_ARRAY_HPP
