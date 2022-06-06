package jv.com.cain.algorithm;

@SuppressWarnings("JavaDoc")
public class MyAlgorithm {
    public static void main(String[] args) {
        System.out.println("==========test Subject43 CuntDigitOne==========");
        testSubject43CuntDigitOne();
        System.out.println("==========test Subject49 NthUglyNumber==========");
        testSubject49NthUglyNumber();
    }

    /**
     * refe: https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/1n-zheng-shu-zhong-1-chu-xian-de-ci-shu-umaj8/ method1
     * Time complexity: O(logn)
     * Spatial complexity: O(1)
     *
     * @param n
     * @return
     */
    public static int subject43CuntDigitOne(int n) {
        int multi = 1;
        int result = 0;
        while (n >= multi) {
            result += (n / (multi * 10)) * multi + Math.min(Math.max(n % (multi * 10) - multi + 1, 0), multi);
            multi *= 10;
        }
        return result;
    }

    public static void testSubject43CuntDigitOne() {
        int n = 100;
        System.out.println("num of 1: " + subject43CuntDigitOne(n));
    }

    /**
     * refe: https://leetcode.cn/problems/chou-shu-lcof/solution/chou-shu-by-leetcode-solution-0e5i/ method2
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param n
     * @return
     */
    public static Integer subject49NthUglyNumber(int n) {
        if (n <= 0) {
            return null;
        }
        int[] uglyNums = new int[n + 1];
        uglyNums[1] = 1;
        // multiple 2、3、5 critical point
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            // find next ugly num
            int num2 = uglyNums[p2] * 2, num3 = uglyNums[p3] * 3, num5 = uglyNums[p5] * 5;
            int next = Math.min(Math.min(num2, num3), num5);
            // point increment
            if (next == num2) {
                p2++;
            }
            if (next == num3) {
                p3++;
            }
            if (next == num5) {
                p5++;
            }
            uglyNums[i] = next;
        }
        return uglyNums[n];
    }

    public static void testSubject49NthUglyNumber() {
        int n = 10;
        System.out.println("the " + n + "th ugly num = " + subject49NthUglyNumber(n));
    }
}
