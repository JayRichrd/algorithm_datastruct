package jv.com.cain.algorithm.bit;

public class BitOperation {
    public static void main(String[] args) {
        System.out.println("==========test subject9 HammingWeight==========");
        testSubject9HammingWeight();
    }

    /**
     * refe: https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/solution/er-jin-zhi-zhong-1de-ge-shu-by-leetcode-50bb1/ method1
     * Time complexity: O(K)
     * @param n
     * @return
     */
    public static int subject15HammingWeight1(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                result++;
            }
        }
        return result;
    }

    /**
     * refe: https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/solution/er-jin-zhi-zhong-1de-ge-shu-by-leetcode-50bb1/ method2
     * Time complexity: O(logn)
     * @param n
     * @return
     */
    public static int subject15HammingWeight2(int n) {
        int result = 0;
        while (n != 0) {
            result++;
            n = n & (n - 1);
        }
        return result;
    }

    public static void testSubject9HammingWeight(){
        int n = 6;
        System.out.println("num of 1 = " + subject15HammingWeight1(n));
        System.out.println("num of 1 = " + subject15HammingWeight2(n));
    }
}

