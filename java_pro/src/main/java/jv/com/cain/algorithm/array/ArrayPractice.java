package jv.com.cain.algorithm.array;

import java.util.*;

@SuppressWarnings("JavaDoc")
public class ArrayPractice {
    public static void main(String[] args) {
        System.out.println("==========test threeSum==========");
        testThreeSum();
        System.out.println("==========test firstMissingPositive==========");
        testFirstMissingPositive();
        System.out.println("==========test Subject21 Exchange==========");
        testSubject21Exchange();
        System.out.println("==========test Subject29 PrintMatrixSpiral==========");
        testSubject29PrintMatrixSpiral();
        System.out.println("==========test Subject39 MajorElement==========");
        testSubject39MajorElement();
        System.out.println("==========test Subject42 MaxSubArray==========");
        testSubject42MaxSubArray();
        System.out.println("==========test Subject57 TwoSum==========");
        testSubject57TwoSum();
    }

    /**
     * refe: https://leetcode-cn.com/problems/3sum/solution/pai-xu-shuang-zhi-zhen-zhu-xing-jie-shi-python3-by/ method1
     * Time complexity：O(n^2)
     * spatial complexity：O(1)
     *
     * @param inNums input array
     * @return the result list
     */
    public static List<List<Integer>> threeSum(int[] inNums) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (inNums.length < 3) {
            return resultList;
        }

        /*
         * sort the array firstly
         */
        Arrays.sort(inNums);

        int len = inNums.length;
        for (int i = 0; i < len; i++) {
            /*
             * as the array is ascending, so three num sum must be larger than zero since i index
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

            // first place
            int cur = inNums[i];
            /*
             * left and right pointer
             * second place and third place
             */
            int pL = i + 1, pR = len - 1;
            while (pL < pR) {
                int temp = cur + inNums[pL] + inNums[pR];
                // find one result
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
    public static Integer firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int len = nums.length;
        // the final result must belongs to [1,len+1]
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
     *
     * @param nums
     */
    public static void subject21Exchange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int len = nums.length;
        int start = 0, end = len - 1;
        while (start < end) {
            while (start < end && nums[start] % 2 == 1) start++;
            while (start < end && nums[end] % 2 == 0) end--;
            // exchange
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
        System.out.println();
    }

    /**
     * refe: https://leanote.com/note/59827b86ab6441231e000e18
     * Time complexity: O(m*n)
     * Spatial complexity: O(1)
     *
     * @param matrix
     */
    public static void subject29PrintMatrixSpiral(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int rows = matrix.length;
        if (rows <= 0) {
            return;
        }
        int columns = matrix[0].length;
        if (columns <= 0) {
            return;
        }

        int start = 0;
        while (2 * start < rows && 2 * start < columns) {
            System.out.print("the " + start + "th circle: ");
            printMatrixCircle(matrix, start, rows, columns);
            System.out.println();
            start++;
        }
    }

    private static void printMatrixCircle(int[][] matrix, int start, int rows, int columns) {
        int endX = columns - 1 - start;
        int endY = rows - 1 - start;
        // left -> right
        for (int i = start; i <= endX; i++) {
            System.out.print(matrix[start][i] + ", ");
        }
        // top -> bottom
        if (start < endY) {
            for (int i = start + 1; i <= endY; i++) {
                System.out.print(matrix[i][endX] + ", ");
            }
        }
        // right -> left
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; i--) {
                System.out.print(matrix[endY][i] + ", ");
            }
        }
        // bottom -> top
        if (start < endY - 1 && start < endX) {
            for (int i = endY - 1; i > start; i--) {
                System.out.print(matrix[i][start] + ", ");
            }
        }
    }

    public static void testSubject29PrintMatrixSpiral() {
        int[][] matrix = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        subject29PrintMatrixSpiral(matrix);
    }

    /**
     * refe: https://leetcode-cn.com/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/ method5
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param nums data which search major element from
     * @return the major element
     */
    public static Integer subject39MajorElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            // change candidate value
            if (count == 0) {
                candidate = num;
            }
            count += num == candidate ? +1 : -1;
        }
        return candidate;
    }

    public static void testSubject39MajorElement() {
        int[] nums = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println("major element: " + subject39MajorElement(nums));
    }

    /**
     * refe: https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/solution/lian-xu-zi-shu-zu-de-zui-da-he-by-leetco-tiui/ method
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param nums
     * @return
     */
    public static Integer subject42MaxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int max = nums[0], pre = 0;
        for (int num : nums) {
            // think about num should be added into pre or num is single
            pre = Math.max(pre + num, num);
            max = Math.max(pre, max);
        }
        return max;
    }

    public static void testSubject42MaxSubArray() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("max sub array sum: " + subject42MaxSubArray(nums));
    }

    /**
     * refe: https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/solution/mian-shi-ti-57-he-wei-s-de-liang-ge-shu-zi-shuang-/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<Integer> subject57TwoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                result.add(nums[i]);
                result.add(nums[j]);
                break;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }

    public static void testSubject57TwoSum() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println("sum = " + target + ": " + subject57TwoSum(nums, target));
    }

    /**
     * refe: https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/solution/mian-shi-ti-03-shu-zu-zhong-zhong-fu-de-shu-zi-b-4/
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param nums
     * @return
     */
    public static int subject3RepeatNum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return -1;
    }
}