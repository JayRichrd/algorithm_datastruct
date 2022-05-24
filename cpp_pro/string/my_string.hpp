//
// Created by cainjiang on 2022/5/24.
//

#ifndef CPP_PRO_MY_STRING_HPP
#define CPP_PRO_MY_STRING_HPP

#include <string>
#include <vector>

namespace string_practice {
    using namespace std;

    class MyString {
    public:
        /**
         * Time complexity: O(n * m)
         * @param source
         * @param target
         * @return return index of first target in source
         */
        static int find_sub_str_bf(string source, string target);
        static void test_find_sub_str_bf();

        /**
         * refe: https://leetcode.cn/problems/reverse-string/solution/fan-zhuan-zi-fu-chuan-by-leetcode-solution/
         * Time complexity: O(n)
         * Spatial complexity: O(1)
         * @param str
         */
        static void revert_string(vector<char> &str);
        static void test_revert_string();

        /**
         * refe: https://leetcode.cn/problems/reverse-words-in-a-string/solution/fan-zhuan-zi-fu-chuan-li-de-dan-ci-by-leetcode-sol/ method3
         * Time complexity: O(n)
         * Spatial complexity: O(n)
         * @param str
         * @return return the revert str
         */
        static string revert_words(string str);
        static void test_revert_words();
    };
}
#endif //CPP_PRO_MY_STRING_HPP
