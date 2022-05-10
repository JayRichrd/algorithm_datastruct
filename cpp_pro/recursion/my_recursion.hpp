//
// Created by cainjiang on 2022/5/10.
//

#ifndef CPP_PRO_MY_RECURSION_HPP
#define CPP_PRO_MY_RECURSION_HPP

#include <iostream>

namespace recursive_practice {
    using namespace std;

    /**
     * fibonacci sequence by recursion
     * f(1) = 1, f(2) = 1
     * f(n) = f(n-1) + f(n-2)
     * Time complexity: O(1)
     * Spatial complexity: O(n)
     * @param n
     * @return
     */
    int fibonacci_sequence(int n) {
        if (n == 1 || n == 2) return 1;
        return fibonacci_sequence(n - 1) + fibonacci_sequence(n - 2);
    }

    void test_fibonacci_sequence() {
        int n = 10;
        cout << "f(" << n << ") = " << fibonacci_sequence(n) << endl;
    }

    /**
     * factorial by recursion
     * f(1) = 1
     * f(n) = f(n-1) * n
     * @param n
     * @return
     */
    int factorial(int n) {
        if (n == 1) return 1;
        return factorial(n - 1) * n;
    }

    void test_factorial() {
        int n = 10;
        cout << "f(" << n << ") = " << factorial(n) << endl;
    }

}
#endif //CPP_PRO_MY_RECURSION_HPP
