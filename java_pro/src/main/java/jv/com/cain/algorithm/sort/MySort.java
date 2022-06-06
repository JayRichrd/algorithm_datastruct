package jv.com.cain.algorithm.sort;

import java.util.Arrays;

@SuppressWarnings("JavaDoc")
public class MySort {
    public static void main(String[] args) {
        System.out.println("==========test Subject45 minNums==========");
        testSubject45minNums();
        System.out.println("==========test Subject51 ReversePairs==========");
        testSubject51ReversePairs();
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
        // convert to string for big number
        for (int i = 0; i < len; i++) {
            numsStr[i] = String.valueOf(nums[i]);
        }
        // sort
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

    /**
     * refe: https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/solution/shu-zu-zhong-de-ni-xu-dui-by-leetcode-solution/ method1
     * Time complexity: O(n*logn)
     * Spatial complexity: O(n)
     *
     * @param nums
     * @return
     */
    public static Integer subject51ReversePairs(int[] nums) {
        if (nums == null) {
            return null;
        }
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        int[] copy = new int[len];
        System.arraycopy(nums, 0, copy, 0, len);
        // use the array temp repeatedly
        int[] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);
    }

    private static int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);
        // if left part is smaller than right part, there is no reverse pair cross
        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }
        int crossPair = mergeAndCountReversPairs(nums, left, mid, right, temp);
        return leftPairs + crossPair + rightPairs;
    }

    private static int mergeAndCountReversPairs(int[] nums, int left, int mid, int right, int[] temp) {
        if (right + 1 - left >= 0) System.arraycopy(nums, left, temp, left, right + 1 - left);
        int i = left, j = mid + 1;
        int crossPairs = 0;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {// just remain right part
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {// just remain left part
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
                // the j index num contribute reverse pair
                crossPairs += mid - i + 1;
            }
        }
        return crossPairs;
    }

    public static void testSubject51ReversePairs() {
        int[] nums = {7, 5, 6, 4};
        System.out.println("num of revers pair: " + subject51ReversePairs(nums));
    }
}
