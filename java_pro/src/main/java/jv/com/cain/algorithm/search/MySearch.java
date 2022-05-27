package jv.com.cain.algorithm.search;

@SuppressWarnings("JavaDoc")
public class MySearch {
    public static void main(String[] args) {
        System.out.println("==========test Subject3 Find==========");
        testSubject3Find();
        System.out.println();
        System.out.println("==========test Subject8 MinArray==========");
        testSubject8MinArray();
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
    public static boolean subject3Find(int[][] matrix, int num) {
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
        System.out.println(number + " exist in matrix：" + subject3Find(matrix, number));
    }

    /**
     * refe: https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solution/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-by-leetcode-s/ method1
     * Time complexity: O(logn)
     * Spatial complexity: O(1)
     *
     * @param numbers
     * @return
     */
    public static int subject8MinArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot = low + ((high - low) >> 1);
            // can not assign high = pivot -1 to void the min
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high --;
            }
        }
        return numbers[low];
    }

    public static void testSubject8MinArray() {
        int[] numbers1 = {3, 4, 5, 1, 2};
        int min = subject8MinArray(numbers1);
        System.out.println("min = " + min);
        int[] numbers2 = {1, 0, 1, 1, 1};
        min = subject8MinArray(numbers2);
        System.out.println("min = " + min);
    }
}
