package jv.com.cain.algorithm.string;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("JavaDoc")
public class MyString {
    public static void main(String[] args) {
        System.out.println("==========test Subject4 ReplaceSpace==========");
        testSubject4ReplaceSpace();
        System.out.println("==========test Subject38 Permutation==========");
        testSubject38Permutation();
    }

    /**
     * refe: https://leetcode.cn/problems/ti-huan-kong-ge-lcof/solution/mian-shi-ti-05-ti-huan-kong-ge-by-leetcode-solutio/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param str
     * @return
     */
    public static String subject4ReplaceSpace(String str) {
        if (str.isEmpty()) {
            return str;
        }
        int length = str.length();
        char[] strArray = new char[length * 3];
        int capacity = 0;
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                strArray[capacity++] = '%';
                strArray[capacity++] = '2';
                strArray[capacity++] = '0';
            } else {
                strArray[capacity++] = c;
            }
        }
        return new String(strArray, 0, capacity);
    }

    public static void testSubject4ReplaceSpace() {
        String str = "we are happy.";
        System.out.println("replaced str: " + subject4ReplaceSpace(str));
    }

    /**
     * refe: https://leanote.com/note/59827b86ab6441231e000e18
     * Time complexity: O(n * n!)
     * Spatial complexity: O(n)
     *
     * @param str
     */
    public static void subject38Permutation(String str) {
        if (str.isEmpty()) {
            return;
        }
        char[] chars = str.toCharArray();
        permutationRecursive(chars, 0);
    }

    private static void permutationRecursive(char[] chars, int begin) {
        int len = chars.length;
        // finish recursive and print
        if (begin == len - 1) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.print(", ");
        } else {
            for (int i = begin; i < len; i++) {
                swap(chars, begin, i);
                permutationRecursive(chars, begin + 1);
                // must restore
                swap(chars, begin, i);
            }
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void testSubject38Permutation() {
        String str = "abc";
        System.out.println("permutation of " + str + ": ");
        subject38Permutation(str);
        System.out.println();
    }

    /**
     * refe: https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/solution/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-by-3zqv5/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param str
     * @return
     */
    public static char subject50FirstUniqChar(String str) {
        // hashmap key is not sorted
        Map<Character, Integer> frequency = new HashMap<>();
        int len = str.length();
        if (len <= 0) {
            return ' ';
        }
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < len; i++) {
            // use str sequence
            if (frequency.get(str.charAt(i)) == 1) {
                return str.charAt(i);
            }
        }
        return ' ';
    }
}
