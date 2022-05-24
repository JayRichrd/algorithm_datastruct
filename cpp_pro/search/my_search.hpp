//
// Created by cainjiang on 2022/5/24.
//

#ifndef CPP_PRO_MY_SEARCH_HPP
#define CPP_PRO_MY_SEARCH_HPP

#include <vector>

namespace search_practice {
    using namespace std;

    class MySearch {
    private:
        /**
         * binary search with recursion
         * @param nums
         * @param k the target number
         * @param low
         * @param high
         * @return
         */
        static int binary_search_do_recursion(const vector<int> &nums, int k, int low, int high);

    public:
        /**
         * Time complexity: O(logn)
         * @param nums
         * @param k the target number
         * @return return index of k in nums if found, or -1
         */
        static int binary_search_by_iteration(const vector<int> &nums, int k);
        static int binary_search_by_recursion(const vector<int> &nums, int k);
        static void test_binary_search();

        /**
         * refe: https://leetcode.cn/problems/search-insert-position/solution/sou-suo-cha-ru-wei-zhi-by-leetcode-solution/ method1
         * Time complexity: O(logn)
         * @param nums
         * @param k
         * @return
         */
        static int search_insert(const vector<int> &nums, int k);
        static void test_search_insert();

        /**
         * refe: https://leetcode.cn/problems/sqrtx/solution/x-de-ping-fang-gen-by-leetcode-solution/ method2
         * Time complexity: O(logk)
         * @param k
         * @return
         */
        static int my_sqrt(int k);
        static void test_my_sqrt();
    };
}
#endif //CPP_PRO_MY_SEARCH_HPP
