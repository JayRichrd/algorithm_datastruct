package jv.com.cain.algorithm.stack;

import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

@SuppressWarnings("unused")
public class MinStack {
    Deque<Integer> dataStack;
    Deque<Integer> minStack;

    public MinStack() {
        dataStack = new LinkedList<>();
        minStack = new LinkedList<>();
    }

    public void push(int data) {
        dataStack.push(data);
        if (minStack.isEmpty()) {
            minStack.push(data);
        } else {
            // every time push one min element
            minStack.push(Math.min(minStack.peek(), data));
        }
    }

    @Nullable
    public Integer pop() throws NoSuchElementException {
        // also pop one element
        minStack.pop();
        return dataStack.pop();
    }

    @Nullable
    public Integer min() {
        // just return min, not pop
        return minStack.peek();
    }
}
