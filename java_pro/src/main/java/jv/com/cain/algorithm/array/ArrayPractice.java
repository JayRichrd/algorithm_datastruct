package jv.com.cain.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayPractice {
    public static void main(String[] args) {
        System.out.println("==========test threeSum==========");
        testThreeSum();
    }

    static void testThreeSum() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println("result: " + threeSum(nums));
    }

    /**
     * refe: https://leetcode-cn.com/problems/3sum/solution/pai-xu-shuang-zhi-zhen-zhu-xing-jie-shi-python3-by/
     * Time complexity：O(n^2)
     * spatial complexity：O(1)
     *
     * @param inNums input array
     * @return the result list
     */
    static List<List<Integer>> threeSum(int[] inNums) {
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
}