package jv.com.cain.algorithm.queue;

import java.util.ArrayDeque;
import java.util.Deque;

@SuppressWarnings("unused")
public class QueueByStack<T> {
    private final Deque<T> inStack;
    private final Deque<T> outStack;

    public QueueByStack() {
        this.inStack = new ArrayDeque<>();
        this.outStack = new ArrayDeque<>();
    }

    public void appendTail(T item) {
        inStack.push(item);
    }

    public T deleteHead() {
        if (outStack.isEmpty()) {
            if (inStack.isEmpty()) {
                return null;
            }
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }
}
