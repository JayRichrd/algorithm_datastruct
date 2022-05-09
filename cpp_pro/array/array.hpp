//
// Created by cainjiang on 2022/4/27.
//
#include <vector>

#ifndef CPP_PRO_ARRAY_HPP
#define CPP_PRO_ARRAY_HPP
namespace array_practice {
    class ArrayPractice {
    public:
        /**
         * refe: https://leetcode-cn.com/problems/merge-sorted-array/ method3
         * array1‘s size is larger than array2's size, and size of array1 equals array1's valid element plus array2 size
         * Time complexity: O(m+size)
         * Spatial complexity: O(1)
         * @param array1
         * @param n top of array1's valid element
         * @param array2
         * @param m top of array2's valid element
         */
        static void merge_sorted_array(int array1[], int n, const int array2[], int m);
        static void test_merge_sorted_array();

        /**
         * refe: https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/ method1
         * Time complexity: O(size)
         * Spatial complexity: O(size)
         * @param nums data which search major element from
         * @return the major element
         */
        static int majorElement1(std::vector<int> &nums);
        /**
         * refe: https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/ method5
         * Time complexity: O(size)
         * Spatial complexity: O(1)
         * @param nums data which search major element from
         * @return the major element
         */
        static int majorElement2(std::vector<int> &nums);
        static void test_major_element();
    };

}
#endif //CPP_PRO_ARRAY_HPP
