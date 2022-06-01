package jv.com.cain.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

@SuppressWarnings("JavaDoc")
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
}
