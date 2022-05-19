//
// Created by cainjiang on 2022/5/10.
//

#ifndef CPP_PRO_MY_RECURSION_HPP
#define CPP_PRO_MY_RECURSION_HPP

#include <iostream>

namespace recursive_practice {
    using namespace std;

    class MyRecursion {
    public:
        /**
        * fibonacci sequence by recursion
        * f(1) = 1, f(2) = 1
        * f(n) = f(n-1) + f(n-2)
        * Time complexity: O(1)
        * Spatial complexity: O(n)
        * @param n
        * @return
        */
        static int fibonacci_sequence(int n);
        static void test_fibonacci_sequence();

        /**
         * factorial by recursion
         * f(1) = 1
         * f(n) = f(n-1) * n
         * @param n
         * @return
         */
        static int factorial(int n);
        static void test_factorial();

        /**
         * refe: https://leetcode.cn/problems/climbing-stairs/
         * @param n
         * @return
         */
        static int climb_stairs_recursion(int n);
        static int climb_stairs_iteration(int n);
        static void test_climb_stairs();
    };

}
#endif //CPP_PRO_MY_RECURSION_HPP
