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

    /**
     * Time complexity: O(n^2)
     * Spatial complexity: O(1)
     * Stable
     *
     * @param nums
     */
    public static void bubbleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int len = nums.length;
        boolean exchange;
        for (int i = 0; i < len; i++) {
            exchange = false;
            // find correct place for every element
            for (int j = 0; j < len - i - 1; j++) {
                // do not exchange for stability when equal
                if (nums[j] > nums[j + 1]) {
                    exchange = true;
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
            // if no exchange, it is sorted, finish immediately
            if (!exchange) {
                break;
            }
        }
    }

    /**
     * Time complexity: O(n^2)
     * Spatial complexity: O(1)
     * Stable
     *
     * @param nums
     */
    public static void insertSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int num = nums[i];
            int j = i - 1;
            // find and move from back to front
            while (j >= 0) {
                // find suitable place that is first smaller num
                if (nums[j] < num) {
                    break;
                }
                // move element to back
                nums[j + 1] = nums[j];
                j--;
            }
            // insert element
            nums[j + 1] = num;
        }
    }

    /**
     * Time complexity: O(n^2)
     * Spatial complexity: O(1)
     * Unstable
     *
     * @param nums
     */
    public static void selectSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int len = nums.length;
        int minIndex;
        for (int i = 0; i < len; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }
        }
    }

    /**
     * Time complexity: O(n*logn)
     * Spatial complexity: O(n)
     * Stable
     *
     * @param nums
     */
    public static void mergeSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        mergeSortRecursive(nums, 0, nums.length - 1);
    }

    private static void mergeSortRecursive(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        // divide and continue
        int mid = start + ((end - start) >> 1);
        mergeSortRecursive(nums, start, mid);
        mergeSortRecursive(nums, mid + 1, end);
        // merge left and right part
        int[] temp = new int[end - start + 1];
        int count = 0;
        int i = start, j = mid + 1;
        while (i <= mid && j <= end) {
            if (nums[i] < nums[j]) {
                temp[count++] = nums[i++];
            } else {
                temp[count++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[count++] = nums[i++];
        }
        while (j <= end) {
            temp[count++] = nums[j++];
        }
        // copy data back
        count = 0;
        for (int k = start; k <= end; k++) {
            nums[k] = temp[count++];
        }
    }
}
