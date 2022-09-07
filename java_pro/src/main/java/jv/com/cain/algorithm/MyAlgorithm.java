package jv.com.cain.algorithm;

import java.util.*;

@SuppressWarnings({"JavaDoc", "SuspiciousNameCombination"})
public class MyAlgorithm {
    public static void main(String[] args) {
        System.out.println("==========test Subject43 CuntDigitOne==========");
        testSubject43CuntDigitOne();
        System.out.println("==========test Subject49 NthUglyNumber==========");
        testSubject49NthUglyNumber();
        System.out.println("==========test Subject56 SingleNum==========");
        testSubject56SingleNum();
        System.out.println("==========test Subject56II SingleNum==========");
        testSubject56IISingleNum();
        System.out.println("==========test Subject60 DicesProbability==========");
        testSubject60DicesProbability();
        System.out.println("==========test Subject61 IsStrait==========");
        testSubject61IsStrait();
        System.out.println("==========test Subject62 LastRemain==========");
        testSubject62LastRemain();
        System.out.println("==========test Subject65 Add==========");
        testSubject65Add();
        System.out.println("==========test Subject63 MaxProfit==========");
        testSubject63MaxProfit();
        System.out.println("==========test Subject64 SumNums==========");
        testSubject64SumNums();
        System.out.println("==========test Subject66 ConstructArr==========");
        testSubject66ConstructArr();
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
        // multiple 2/3/5 critical point
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            // find next ugly num
            int num2 = uglyNums[p2] * 2, num3 = uglyNums[p3] * 3, num5 = uglyNums[p5] * 5;
            int next = Math.min(Math.min(num2, num3), num5);
            // point increment
            if (next == num2) {
                p2++;
            } else if (next == num3) {
                p3++;
            } else {// next == num5
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

    /**
     * refe: https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-by-leetcode/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param nums
     * @return
     */
    public static List<Integer> subject56SingleNum(int[] nums) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        // find first 1 from low to high
        int div = 1;
        while ((div & result) == 0) {
            div <<= 1;
        }

        int a = 0, b = 0;
        for (int num : nums) {
            // divide into two groups
            if ((div & num) != 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        List<Integer> singleNums = new ArrayList<>();
        singleNums.add(a);
        singleNums.add(b);
        return singleNums;
    }

    /**
     * refe: https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/solution/mian-shi-ti-56-ii-shu-zu-zhong-shu-zi-chu-xian-d-4/ method2
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param nums
     * @return
     */
    public static int subject56IISingleNum(int[] nums) {
        int[] counts = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                // index 0 is low digit, from low digit to high digit
                counts[i] += num & 1;
                num >>>= 1;
            }
        }

        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            // from high digit to low digit
            res |= counts[31 - i] % 3;
        }
        return res;
    }

    public static void testSubject56IISingleNum() {
        int[] nums = {3, 4, 3, 3};
        System.out.println("single nums: " + subject56IISingleNum(nums));
    }

    public static void testSubject56SingleNum() {
        int[] nums = {1, 1, 2, 3, 3, 4, 5, 5};
        System.out.println("single nums: " + subject56SingleNum(nums));
    }

    /**
     * refe: https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/solution/jian-zhi-offer-60-n-ge-tou-zi-de-dian-sh-z36d/ method2
     * Time complexity: O(n^2)
     * Spatial complexity: O(n)
     *
     * @param n
     * @return
     */
    public static double[] subject60DicesProbability(int n) {
        if (n <= 0) {
            return null;
        }
        int maxDiceNum = 6;
        // first dice
        double[] result = new double[6];
        Arrays.fill(result, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            // next number dices probability
            double[] temp = new double[maxDiceNum * i - i + 1];
            Arrays.fill(temp, 0.0);
            int len = result.length;
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < maxDiceNum; k++) {
                    temp[j + k] += result[j] / maxDiceNum;
                }
            }
            // continue
            result = temp;
        }
        return result;
    }

    public static void testSubject60DicesProbability() {
        int n = 3;
        double[] result = subject60DicesProbability(n);
        if (result != null) {
            int len = result.length;
            for (int i = 0; i < len; i++) {
                System.out.println((i + n) + "'s probability = " + result[i]);
            }
        }
    }

    /**
     * refe: https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof/solution/mian-shi-ti-61-bu-ke-pai-zhong-de-shun-zi-ji-he-se/ method1
     * Time complexity: O(1)
     * Spatial complexity: O(1)
     *
     * @param nums
     * @return
     */
    public static boolean subject61IsStrait(int[] nums) {
        int threshHold = 5;
        if (nums == null || nums.length != threshHold) {
            return false;
        }
        int min = 14, max = 0;
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            if (num == 0) continue;
            max = Math.max(max, num);
            min = Math.min(min, num);
            // repeated num return false immediately
            if (!hashSet.add(num)) {
                return false;
            }
        }
        return max - min < threshHold;
    }

    public static void testSubject61IsStrait() {
        int[] nums = {0, 1, 1, 3, 4};
        System.out.println("is strait: " + subject61IsStrait(nums));
    }

    /**
     * refe: https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-by-lee/ method2
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param n
     * @param m
     * @return
     */
    public static int subject62LastRemain(int n, int m) {
        int result = 0;
        for (int i = 2; i <= n; i++) {
            // avoid out of index
            result = (result + m) % i;
        }
        return result;
    }

    public static void testSubject62LastRemain() {
        int n = 10;
        int m = 3;
        System.out.println("f(" + n + "," + m + ") = " + subject62LastRemain(10, 3));
    }

    /**
     * refe: https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/solution/mian-shi-ti-65-bu-yong-jia-jian-cheng-chu-zuo-ji-7/ method1
     * Time complexity: O(1)
     * Spatial complexity: O(1)
     *
     * @param a
     * @param b
     * @return
     */
    public static int subject65Add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }

    public static void testSubject65Add() {
        int a = 3, b = -5;
        System.out.println(a + " + " + b + " = " + subject65Add(a, b));
    }

    /**
     * refe: https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solution/ji-qi-ren-de-yun-dong-fan-wei-by-leetcode-solution/ method1
     * Time complexity: O(m*n)
     * Spatial complexity: O(m*n)
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public static int subject13MovingCount(int m, int n, int k) {
        if (m <= 0 || n <= 0 || k < 0) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }

        // bride visit que, every element is an matrix node[x,y]
        Queue<int[]> queue = new LinkedList<>();
        // some element[x,y] is visited
        boolean[][] visited = new boolean[m][n];
        // to right[0,1] and down[1,0]
        int[] dx = {0, 1};
        int[] dy = {1, 0};

        // offer the first element[0,0]
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        int result = 1;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            // to down and right
            for (int i = 0; i < 2; i++) {
                int nextX = cell[0] + dx[i];
                int nextY = cell[1] + dy[i];
                // check next element is ok
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m || visited[nextX][nextY] || (get(nextX) + get(nextY) > k)) {
                    continue;
                }
                queue.offer(new int[]{nextX, nextY});
                visited[nextX][nextY] = true;
                result++;
            }
        }
        return result;
    }

    /**
     * get sum of every digit
     *
     * @param x
     * @return
     */
    private static int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }

    /**
     * refe: https://leetcode.cn/problems/jian-sheng-zi-lcof/solution/mian-shi-ti-14-i-jian-sheng-zi-tan-xin-si-xiang-by/ method1
     * Time complexity: O(1)
     * Spatial complexity: O(1)
     *
     * @param n
     * @return
     */
    public static int subject14CuttingRope(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n <= 3) {
            return n - 1;
        }
        // n = a*3 + b
        int a = n / 3;
        int b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        if (b == 1) {
            return (int) (Math.pow(3, a - 1) * 2 * 2);
        }
        return (int) (Math.pow(3, a) * 2);
    }

    /**
     * refe: https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/solution/mian-shi-ti-47-li-wu-de-zui-da-jie-zhi-dong-tai-gu/ method1
     * Time complexity: O(m*n)
     * Spatial complexity: O(1)
     *
     * @param matrix
     * @return
     */
    public static Integer subject47MaxValue(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 && j == 0) {
                } else if (i == 0) {// first row
                    matrix[i][j] += matrix[i][j - 1];
                } else if (j == 0) {// first column
                    matrix[i][j] += matrix[i - 1][j];
                } else {// from left and up to current
                    matrix[i][j] += Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }
        return matrix[rows - 1][columns - 1];
    }

    /**
     * refe: https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/solution/gu-piao-de-zui-da-li-run-by-leetcode-sol-0l1g/ method2
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param nums
     * @return
     */
    public static Integer subject63MaxProfit(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int num : nums) {
            if (num < minPrice) {// find min buy price
                minPrice = num;
            } else if (num - minPrice > maxProfit) {// compute max profit
                maxProfit = num - minPrice;
            }
        }
        return maxProfit;
    }

    public static void testSubject63MaxProfit() {
        int[] nums = {7, 1, 5, 3, 6, 4};
        System.out.println("maxProfit = " + subject63MaxProfit(nums));
    }

    /**
     * refe: https://leetcode.cn/problems/qiu-12n-lcof/solution/qiu-12n-by-leetcode-solution/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param n
     * @return
     */
    public static int subject64SumNums(int n) {
        boolean flag = n > 0 && (n += subject64SumNums(n - 1)) > 0;
        return n;
    }

    public static void testSubject64SumNums() {
        int n = 3;
        System.out.println("sum = " + subject64SumNums(n));
    }

    /**
     * refe: https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/solution/mian-shi-ti-66-gou-jian-cheng-ji-shu-zu-biao-ge-fe/
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param nums
     * @return
     */
    public static int[] subject66ConstructArr(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int len = nums.length;
        int[] b = new int[len];
        b[0] = 1;
        int temp = 1;
        // compute bottom
        for (int i = 1; i < len; i++) {
            b[i] = b[i - 1] * nums[i - 1];
        }
        // compute top
        for (int i = len - 2; i >= 0; i--) {
            temp *= nums[i + 1];
            b[i] *= temp;
        }
        return b;
    }

    public static void testSubject66ConstructArr() {
        int[] nums = {1, 2, 3, 4, 5};
        int[] b = subject66ConstructArr(nums);
        System.out.print("b array: ");
        for (int num : b) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }

    /**
     * refe: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/solution/best-time-to-buy-and-sell-stock-ii-zhuan-hua-fa-ji/
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     * @param nums
     * @return
     */
    public static Integer maxProfit(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int maxProfit = 0;
        int temp;
        for (int i = 1; i < nums.length; i++) {
            temp = nums[i] - nums[i - 1];
            if (temp > 0) {
                maxProfit += temp;
            }
        }
        return maxProfit;
    }
}
