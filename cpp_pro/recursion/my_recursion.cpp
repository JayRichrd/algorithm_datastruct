//
// Created by cainjiang on 2022/5/13.
//
#include "my_recursion.hpp"
#include <iostream>

namespace recursive_practice {
    using namespace std;

    int MyRecursion::fibonacci_sequence(int n) {
        if (n == 1 || n == 2) return 1;
        return fibonacci_sequence(n - 1) + fibonacci_sequence(n - 2);
    }

    void MyRecursion::test_fibonacci_sequence() {
        int n = 10;
        cout << "f(" << n << ") = " << fibonacci_sequence(n) << endl;
    }

    int MyRecursion::factorial(int n) {
        if (n == 1) return 1;
        return factorial(n - 1) * n;
    }

    void MyRecursion::test_factorial() {
        int n = 10;
        cout << "f(" << n << ") = " << factorial(n) << endl;
    }

    int MyRecursion::climb_stairs_recursion(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climb_stairs_recursion(n - 1) + climb_stairs_recursion(n - 2);
    }

    int MyRecursion::climb_stairs_iteration(int n) {
        int result = 0;
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int fn_1 = 2;
        int fn_2 = 1;
        for (int i = 3; i <= n; ++i) {
            result = fn_1 + fn_2;
            fn_2 = fn_1;
            fn_1 = result;
        }
        return result;
    }

    void MyRecursion::test_climb_stairs() {
        int n = 10;
        cout<<"n = "<<n<<endl;
        cout<<"result by recursion = " <<climb_stairs_recursion(n)<< endl;
        cout<<"result by iteration = " <<climb_stairs_iteration(n)<< endl;
    }

}
