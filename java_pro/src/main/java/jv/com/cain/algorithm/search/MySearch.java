package jv.com.cain.algorithm.search;

@SuppressWarnings("JavaDoc")
public class MySearch {
    public static void main(String[] args) {
        System.out.println("==========test Subject3 Find==========");
        testSubject3Find();
        System.out.println();
        System.out.println("==========test Subject11 MinArray==========");
        testSubject11MinArray();
        System.out.println("==========test Subject53 numOfTarget==========");
        testSubject53numOfTarget();
    }

    /**
     * refe: https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/solution/mian-shi-ti-04-er-wei-shu-zu-zhong-de-cha-zhao-b-3/ method2
     * Time complexity: O(m + n)
     * Spatial complexity: O(1)
     *
     * @param matrix
     * @param num
     * @return
     */
    public static boolean subject4Find(int[][] matrix, int num) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int columns = matrix[0].length;
        int row = matrix.length - 1;
        int column = 0;
        while (row >= 0 && column < columns) {
            int cur = matrix[row][column];
            if (cur == num) {
                return true;
            } else if (cur < num) {
                column++;
            } else {
                row--;
            }
        }
        return false;
    }

    public static void testSubject3Find() {
        int[][] matrix = new int[][]{{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int number = 20;
        System.out.println(number + " exist in matrix: " + subject4Find(matrix, number));
    }

    /**
     * refe: https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solution/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-by-leetcode-s/ method1
     * Time complexity: O(logn)
     * Spatial complexity: O(1)
     *
     * @param numbers
     * @return
     */
    public static int subject11MinArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        // when low = high break
        while (low < high) {
            int pivot = low + ((high - low) >> 1);
            // can not assign high = pivot -1 to void the min
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot +1;
            } else {
                high--;
            }
        }
        // low = high
        return numbers[low];
    }

    public static void testSubject11MinArray() {
        int[] numbers1 = {3, 4, 5, 1, 2};
        int min = subject11MinArray(numbers1);
        System.out.println("min = " + min);
        int[] numbers2 = {1, 0, 1, 1, 1};
        min = subject11MinArray(numbers2);
        System.out.println("min = " + min);
    }

    /**
     * refe: https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/solution/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-wl6kr/ method1
     * Time complexity: O(logn)
     * Spatial complexity: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public static Integer subject53numOfTarget(int[] nums, int target) {
        if (nums == null) {
            return null;
        }
        int leftIndex = binarySearch(nums, target, true);
        int rightIndex = binarySearch(nums, target, false) - 1;
        if (rightIndex <= nums.length - 1 && leftIndex <= rightIndex && nums[leftIndex] == target && nums[rightIndex] == target) {
            return rightIndex - leftIndex + 1;
        }
        return 0;
    }

    private static int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, result = nums.length;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if ((lower && nums[mid] >= target)/*first >= target index*/ || nums[mid] > target/*first large target index*/) {
                right = mid - 1;
                result = mid;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    public static void testSubject53numOfTarget() {
        int[] nums = {1, 2, 3, 3, 3, 3, 4, 5, 6};
        int target = 7;
        System.out.println("num of " + target + ": " + subject53numOfTarget(nums, target));
    }

    /**
     * common binary search
     * Time complexity: O(logn)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int commonBinarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * find first num that equals to target using binary search
     * Time complexity: O(logn)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int firstNumBinarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] == target) {
                if (mid == 0 || nums[mid - 1] != target) return mid;
                high = mid - 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * find first num that is bigger the target using binary search
     * Time complexity: O(logn)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int firstBigNumBinarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] < target) return mid;
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * refe: https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/solution/mian-shi-ti-53-ii-0n-1zhong-que-shi-de-shu-zi-er-f/
     * Time complexity: O(logn)
     * Spatial complexity: O(1)
     *
     * @param nums
     * @return
     */
    public static Integer subject53IIMissingNum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] == mid) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // low > high
        return low;
    }
}