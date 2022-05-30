package jv.com.cain.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayPractice {
    public static void main(String[] args) {
        System.out.println("==========test threeSum==========");
        testThreeSum();
        System.out.println("==========test firstMissingPositive==========");
        testFirstMissingPositive();
        System.out.println("==========test Subject21 Exchange==========");
        testSubject21Exchange();
    }

    /**
     * refe: https://leetcode-cn.com/problems/3sum/solution/pai-xu-shuang-zhi-zhen-zhu-xing-jie-shi-python3-by/
     * Time complexity：O(n^2)
     * spatial complexity：O(1)
     *
     * @param inNums input array
     * @return the result list
     */
    public static List<List<Integer>> threeSum(int[] inNums) {
        List<List<Integer>> resultList = new ArrayList<>();
        System.out.print("source input array: ");
        for (int num : inNums) {
            System.out.print(num + ", ");
        }
        System.out.println();

        if (inNums.length < 3) {
            return resultList;
        }

        /*
         * sort the array firstly
         */
        Arrays.sort(inNums);
        System.out.print("sorted input array: ");
        for (int num : inNums) {
            System.out.print(num + ", ");
        }
        System.out.println();

        int len = inNums.length;
        for (int i = 0; i < len; i++) {
            /*
             * as the array is ascending, so three num sum must be more than zero since i index
             * now we have to return the result.
             */
            if (inNums[i] > 0) {
                return resultList;
            }

            /*
             * avoid duplicated result in first num place.
             * just skip
             */
            if (i > 0 && inNums[i] == inNums[i - 1]) {
                continue;
            }

            int cur = inNums[i];
            /*
             * left and right pointer
             */
            int pL = i + 1, pR = len - 1;
            while (pL < pR) {
                int temp = cur + inNums[pL] + inNums[pR];
                if (temp == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(cur);
                    list.add(inNums[pL]);
                    list.add(inNums[pR]);
                    resultList.add(list);
                    /*
                     * avoid duplicated result in second num place.
                     * just skip
                     */
                    while (pL < pR && inNums[pL + 1] == inNums[pL]) {
                        pL++;
                    }
                    /*
                     * avoid duplicated result in third num place.
                     * just skip
                     */
                    while (pL < pR && inNums[pR - 1] == inNums[pR]) {
                        pR--;
                    }
                    pL++;
                    pR--;
                } else if (temp < 0) {
                    pL++;
                } else {
                    pR--;
                }
            }
        }

        return resultList;
    }

    public static void testThreeSum() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println("result: " + threeSum(nums));
    }

    /**
     * refe: https://leetcode-cn.com/problems/first-missing-positive/solution/que-shi-de-di-yi-ge-zheng-shu-by-leetcode-solution/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param nums input array
     * @return the first missing positive
     */
    public static int firstMissingPositive(int[] nums) {
        int len = nums.length;
        int result = len + 1;
        /*
         * change negative to len+1 for marking
         */
        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0) {
                nums[i] = len + 1;
            }
        }

        /*
         * find num that belong to [1,N]
         * and mark num-1 index num
         */
        for (int i = 0; i < len; i++) {
            int num = Math.abs(nums[i]);
            if (num <= len) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        /*
         * find first positive num's index,
         * find first missing positive
         */
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return result;
    }

    public static void testFirstMissingPositive() {
        int[] nums = {3, 4, -1, 1, 9};
        System.out.print("input array: ");
        for (int num : nums) {
            System.out.print(num + ", ");
        }
        System.out.println();
        System.out.println("result: " + firstMissingPositive(nums));
    }

    /**
     * refe: https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/solution/mian-shi-ti-21-diao-zheng-shu-zu-shun-xu-shi-qi-4/
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     * @param nums
     */
    public static void subject21Exchange(int[] nums) {
        if (nums == null) {
            return;
        }
        int len = nums.length;
        int start = 0, end = len - 1;
        while (start < end) {
            while (start < end && nums[start] % 2 == 1) start++;
            while (start < end && nums[end] % 2 == 0) end--;
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
    }

    public static void testSubject21Exchange() {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.print("before: ");
        for (int num : nums) {
            System.out.print(num + ", ");
        }
        System.out.println();

        subject21Exchange(nums);
        System.out.print("after: ");
        for (int num : nums) {
            System.out.print(num + ", ");
        }
    }
}