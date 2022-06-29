package jv.com.cain.algorithm.recursion;

@SuppressWarnings("JavaDoc")
public class MyRecursion {
    public static void main(String[] args) {
        System.out.println("==========test fibonacci Sequence==========");
        testFibonacciSequence();
        System.out.println("==========test subject11 MyPow==========");
        testSubject16MyPow();
        System.out.println("==========test subject17 Print1ToMaxOfNDigits==========");
        testSubject17Print1ToMaxOfNDigits();
    }

    /**
     * refe: https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/
     */
    public static int fibonacciSequenceByRecursive(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        return fibonacciSequenceByRecursive(n - 1) + fibonacciSequenceByRecursive(n - 2);
    }

    public static int fibonacciSequenceByIteration(int n) {
        if (n <= 0) {
            return 0;
        }
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
     * refe: https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/solution/mian-shi-ti-10-ii-qing-wa-tiao-tai-jie-wen-ti-dong/
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param n
     * @return
     */
    public static int subject10UpStairs(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        int fn1 = 1, fn2 = 1;
        int fn = fn1 + fn2;
        for (int i = 2; i <= n; i++) {
            fn = fn1 + fn2;
            fn2 = fn1;
            fn1 = fn;
        }
        return fn;
    }

    /**
     * refe: https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/solution/shu-zhi-de-zheng-shu-ci-fang-by-leetcode-yoqr/ method1
     * Time complexity: O(logn)
     * Spatial complexity: O(logn)
     */
    public static double subject16MyPow(double x, long y) {
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

    public static void testSubject16MyPow() {
        long x = 2;
        int y = 5;
        System.out.println("pow(" + 2 + "," + y + ") = " + subject16MyPow(x, y));
    }

    /**
     * refe: https://leanote.com/note/59827b86ab6441231e000e18
     * Time complexity: O(10^n)
     * Spatial complexity: O(10^n)
     *
     * @param n
     */
    public static void subject17Print1ToMaxOfNDigits(int n) {
        if (n < 1) {
            return;
        }

        int[] numbers = new int[n];
        for (int i = 0; i < 10; i++) {
            numbers[0] = i;
            print1ToMaxOfNDigitsRecursive(numbers, 1);
        }
    }

    /**
     * @param numbers
     * @param index   the index of array that to be assigned
     */
    private static void print1ToMaxOfNDigitsRecursive(int[] numbers, int index) {
        // every index place is assigned, so just print the number
        if (index == numbers.length) {
            printNum(numbers);
            return;
        }

        for (int i = 0; i < 10; i++) {
            numbers[index] = i;
            print1ToMaxOfNDigitsRecursive(numbers, index + 1);
        }
    }

    private static void printNum(int[] numbers) {
        boolean flag = false;
        for (int number : numbers) {
            // 0 before valid number should not be printed
            if (number != 0 && !flag) {
                flag = true;
            }
            if (flag) {
                System.out.print(number);
            }
        }
        if (flag) {
            System.out.print(", ");
        }
    }

    public static void testSubject17Print1ToMaxOfNDigits() {
        int n = 2;
        subject17Print1ToMaxOfNDigits(n);
    }

}
