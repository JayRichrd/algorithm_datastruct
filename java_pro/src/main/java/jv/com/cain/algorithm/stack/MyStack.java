package jv.com.cain.algorithm.stack;

import java.util.*;

@SuppressWarnings({"JavaDoc", "unused"})
public class MyStack {
    public static void main(String[] args) {
        System.out.println("==========test Subject31 ValidStackSequence==========");
        testSubject31ValidStackSequence();
    }

    /**
     * refe: https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/solution/mian-shi-ti-31-zhan-de-ya-ru-dan-chu-xu-lie-mo-n-2/
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param push
     * @param pop
     * @return
     */
    public static boolean subject31ValidStackSequence(int[] push, int[] pop) {
        Deque<Integer> helpStack = new ArrayDeque<>();
        int i = 0;
        for (int element : push) {
            // push element
            helpStack.push(element);
            // depend on pop array to pop helpStack
            while (!helpStack.isEmpty() && helpStack.peek() == pop[i]) {
                helpStack.pop();
                i++;
            }
        }
        return helpStack.isEmpty();
    }

    public static void testSubject31ValidStackSequence() {
        int[] push = {1, 2, 3, 4, 5};
        int[] pop = {4, 5, 3, 2, 1};
        System.out.println("is valid: " + subject31ValidStackSequence(push, pop));
    }

    /**
     * refe: https://leetcode.cn/problems/valid-parentheses/solution/you-xiao-de-gua-hao-by-leetcode-solution/
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param str: source string
     * @return return true if the source string bracket valid or false
     */
    public static boolean isValidBrackets(String str) {
        if (str.length() % 2 == 1 || str.length() == 0) {
            return false;
        }
        // template for pair
        Map<Character, Character> pairs = new HashMap<>(4);
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');
        int len = str.length();
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            Character ch = str.charAt(i);
            if (pairs.containsKey(ch)) {// handle right bracket
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    /**
     * refe: https://leetcode.cn/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/ method2
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param str source string
     * @return the max num of valid parentheses
     */
    public static int longestValidBrackets(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        // max valid brackets
        int max = 0;
        Deque<Integer> stack = new LinkedList<>();
        // avoid first char is '('
        // assume bottom is divider ')'
        stack.push(-1);
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else {
                // if ch is ')', pop element for pair firstly
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    /**
     * refe: https://leetcode.cn/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/ method3
     * Time complexity: O(n)
     * Spatial complexity: O(1)
     *
     * @param str source string
     * @return the max num of valid parentheses
     */
    public static int longestValidBrackets1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int max = 0, leftNum = 0, rightNum = 0;
        int len = str.length();
        // left -> right
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch == 'c') {
                leftNum++;
            } else {
                rightNum++;
            }

            // compute max when leftNum equals rightNum
            if (leftNum == rightNum) {
                max = Math.max(max, leftNum * 2);
            } else if (leftNum < rightNum) {
                leftNum = rightNum = 0;
            }
        }

        //reset
        leftNum = rightNum = 0;
        // right -> left
        for (int i = len - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if (ch == ')') {
                rightNum++;
            } else {
                leftNum++;
            }
            // compute max when leftNum equals rightNum
            if (rightNum == leftNum) {
                max = Math.max(max, rightNum * 2);
            } else if (leftNum > rightNum) {
                rightNum = leftNum = 0;
            }
        }
        return max;
    }
}
