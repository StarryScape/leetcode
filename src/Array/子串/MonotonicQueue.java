package Array.子串;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 单调递增队列
 */
public class MonotonicQueue {
    private Deque<Integer> queue; // 使用双端队列实现

    public MonotonicQueue() {
        queue = new ArrayDeque<>();
    }

    // 在队尾添加元素，并确保队列的单调性
    public void push(int val) {
        // 从队尾移除小于val的元素，以保持单调递增
        while (!queue.isEmpty() && queue.peekLast() < val) {
            queue.pollLast();
        }
        queue.offerLast(val);
    }

    // 返回队列中的最大元素（队首元素）
    public int getMax() {
        return queue.peekFirst();
    }

    // 从队首移除元素，如果队首元素等于val
    public void pop(int val) {
        if (!queue.isEmpty() && queue.peekFirst() == val) {
            queue.pollFirst();
        }
    }

    public static void main(String[] args) {
        MonotonicQueue queue = new MonotonicQueue();
        queue.push(4);
        queue.push(2);
        queue.push(3);
        queue.push(1);
        System.out.println("Max: " + queue.getMax()); // 输出最大值 4
        queue.pop(2);
        System.out.println("Max: " + queue.getMax()); // 输出最大值 4
        queue.pop(4);
        System.out.println("Max: " + queue.getMax()); // 输出最大值 3
    }
}