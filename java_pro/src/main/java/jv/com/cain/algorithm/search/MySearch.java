package jv.com.cain.algorithm.search;

public class MySearch {
    public static void main(String[] args) {
        System.out.println("==========test Subject3 Find==========");
        testSubject3Find();
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
}
