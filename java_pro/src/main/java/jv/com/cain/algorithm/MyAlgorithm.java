package jv.com.cain.algorithm;

public class MyAlgorithm {
    public static void main(String[] args) {
        System.out.println("==========test Subject43 CuntDigitOne==========");
        testSubject43CuntDigitOne();
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
}
