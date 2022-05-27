package jv.com.cain.algorithm.recursion;

public class MyRecursion {
    public static void main(String[] args) {
        System.out.println("==========test fibonacci Sequence==========");
        testFibonacciSequence();
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
}
