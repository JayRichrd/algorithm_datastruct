package jv.com.cain.algorithm.sort;

import java.util.Arrays;

@SuppressWarnings("JavaDoc")
public class MySort {
    public static void main(String[] args) {
        System.out.println("==========test Subject45 minNums==========");
        testSubject45minNums();
    }

    /**
     * refe: https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/solution/mian-shi-ti-45-ba-shu-zu-pai-cheng-zui-xiao-de-s-4/ method2
     * Time complexity: O(logn)
     * Spatial complexity: O(n)
     *
     * @param nums
     * @return
     */
    public static String subject45minNums(int[] nums) {
        if (nums == null) {
            return "";
        }
        int len = nums.length;
        String[] numsStr = new String[len];
        for (int i = 0; i < len; i++) {
            numsStr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(numsStr, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder resultSb = new StringBuilder();
        for (String s : numsStr) {
            resultSb.append(s);
        }
        return resultSb.toString();
    }

    public static void testSubject45minNums() {
        int[] nums = {3, 321, 32};
        System.out.println("min num: " + subject45minNums(nums));
    }
}
