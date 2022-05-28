package jv.com.cain.algorithm.recursion;

public class MyRecursion {
    public static void main(String[] args) {
        System.out.println("==========test fibonacci Sequence==========");
        testFibonacciSequence();
        System.out.println("==========test subject11 MyPow==========");
        testSubject11MyPow();
    }

    /**
     * refe: https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/
     */
    public static int fibonacciSequenceByRecursive(int n) {
        if (n <= 2) {
            return 1;
        }
        return fibonacciSequenceByRecursive(n - 1) + fibonacciSequenceByRecursive(n - 2);
    }

    public static int fibonacciSequenceByIteration(int n) {
        if (n <= 2) {
            return 1;
        }
        int fn1 = 1;
        int fn2 = 1;
        int fn = fn1 + fn2;
        for (int i = 3; i <= n; i++) {
            fn = fn1 + fn2;
            // assign fn1 to fn2, and then assign fn to fn1
            fn2 = fn1;
            fn1 = fn;
        }
        return fn;
    }

    public static void testFibonacciSequence() {
        int n = 10;
        System.out.println("f(" + n + ") = " + fibonacciSequenceByRecursive(n));
        System.out.println("f(" + n + ") = " + fibonacciSequenceByIteration(n));
    }

    /**
     * refe: https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/solution/shu-zhi-de-zheng-shu-ci-fang-by-leetcode-yoqr/ method1
     * Time complexity: O(logn)
     * Spatial complexity: O(logn)
     */
    public static double subject11MyPow(double x, long y) {
        long result = 0;
        if (x == 0) {
            return 0;
        }
        return y > 0 ? quickPowByRecursion(x, y) : 1 / quickPowByRecursion(x, -y);
    }

    private static double quickPowByRecursion(double x, long y) {
        if (y == 0) {
            return 1.0;
        }
        double result = quickPowByRecursion(x, y >> 1);
        return y % 2 == 0 ? result * result : result * result * x;
    }

    public static void testSubject11MyPow() {
        long x = 2;
        int y = 5;
        System.out.println("pow(" + 2 + "," + y + ") = " + subject11MyPow(x, y));
    }
}
