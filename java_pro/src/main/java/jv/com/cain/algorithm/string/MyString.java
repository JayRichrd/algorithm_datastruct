package jv.com.cain.algorithm.string;

import java.util.*;

@SuppressWarnings("JavaDoc")
public class MyString {
    public static void main(String[] args) {
        System.out.println("==========test Subject4 ReplaceSpace==========");
        testSubject4ReplaceSpace();
        System.out.println("==========test Subject38 Permutation==========");
        testSubject38Permutation();
        System.out.println("==========test Subject58 RevertWords==========");
        testSubject58RevertWords();
        System.out.println("==========test Subject58II RevertLeftWord==========");
        testSubject58IIRevertLeftWord();
    }

    /**
     * refe: https://leetcode.cn/problems/ti-huan-kong-ge-lcof/solution/mian-shi-ti-05-ti-huan-kong-ge-by-leetcode-solutio/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param str
     * @return
     */
    public static String subject5ReplaceSpace(String str) {
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
        System.out.println("replaced str: " + subject5ReplaceSpace(str));
        System.out.println("replaced str with replaceAppApi: " + str.replaceAll(" ", "%20"));
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
     * Spatial complexity: O(n)
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

    /**
     * refe: https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/solution/fan-zhuan-dan-ci-shun-xu-by-leetcode-sol-vnwj/ method3
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param str
     * @return
     */
    public static String subject58RevertWords(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        // trim
        int begin = 0, end = str.length() - 1;
        while (begin <= end && str.charAt(begin) == ' ') {
            begin++;
        }
        while (begin <= end && str.charAt(end) == ' ') {
            end--;
        }

        // tail is index 0
        Deque<String> que = new LinkedList<>();
        StringBuilder word = new StringBuilder();
        while (begin <= end) {
            char ch = str.charAt(begin);
            // find a whole word, and push it
            if (ch == ' ' && word.length() != 0) {
                //que.offerFirst(word.toString());
                que.push(word.toString());
                // clear word
                word.setLength(0);
            } else if (ch != ' ') {
                word.append(ch);
            }
            begin++;
        }
        // remember the last word
        //que.offerFirst(word.toString());
        que.push(word.toString());
        return String.join(" ", que);
    }

    public static void testSubject58RevertWords() {
        String str = "I am a student.";
        System.out.println(" revert str: " + subject58RevertWords(str));
    }

    /**
     * refe: https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/solution/mian-shi-ti-58-ii-zuo-xuan-zhuan-zi-fu-chuan-qie-p/ method2
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param str
     * @param k
     * @return
     */
    public static String subject58IIRevertLeftWord(String str, int k) {
        if (k <= 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        for (int i = k; i < len + k; i++) {
            sb.append(str.charAt(i % len));
        }
        return sb.toString();
    }

    public static void testSubject58IIRevertLeftWord() {
        String str = "abcdefgh";
        int k = 3;
        System.out.println("left revert " + k + " char result: " + subject58IIRevertLeftWord(str, k));
    }

    /**
     * refe: https://leetcode.cn/problems/reverse-string/solution/fan-zhuan-zi-fu-chuan-by-leetcode-solution/
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param chs
     */
    public static void revertString(char[] chs) {
        if (chs == null || chs.length == 0) {
            return;
        }
        int left = 0, right = chs.length - 1;
        for (; left < right; left++, right--) {
            char temp = chs[left];
            chs[left] = chs[right];
            chs[right] = temp;
        }
    }

    /**
     * refe: https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/solution/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-l4yo/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     * @param str
     * @return
     */
    public static int subject48LengthOfLongestSubString(String str) {
        Set<Character> occ = new HashSet<>();
        int strLen = str.length();
        int right = -1, ans = 0;
        for (int i = 0; i < strLen; i++) {
            // remove left char every time
            if (i != 0) {
                occ.remove(str.charAt(i - 1));
            }
            // move right index
            while (right + 1 < strLen && !occ.contains(str.charAt(right + 1))) {
                right++;
                occ.add(str.charAt(right));
            }
            // compute max
            ans = Math.max(ans, right - i + 1);
        }
        return ans;
    }

}
