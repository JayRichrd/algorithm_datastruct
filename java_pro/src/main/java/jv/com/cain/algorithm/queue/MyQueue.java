package jv.com.cain.algorithm.queue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("JavaDoc")
public class MyQueue {

    /**
     * refe: https://leetcode.cn/problems/sliding-window-maximum/solution/ method2
     * Time complexity: O(n)
     * Spatial complexity: O(k)
     *
     * @param nums
     * @param k
     * @return the max sliding window
     */
    public static List<Integer> slicesWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return null;
        }

        int len = nums.length;
        List<Integer> result = new ArrayList<>(len - k + 1);
        // store index and they are candidate max
        Deque<Integer> deque = new LinkedList<>();
        // the front k element
        for (int i = 0; i < k; i++) {
            // make sure descending
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        result.add(nums[deque.peekFirst()]);

        for (int i = k; i < len; i++) {
            // offer current index
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // make sure that index is in the window
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            result.add(nums[deque.peekFirst()]);
        }
        return result;
    }
}
