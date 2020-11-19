package com.zbkbl;


/**
 * @author liuyang
 * @description
 * @date 2020/08/04 18:24
 **/
public class BlockingQueue {

    private final Object[] items;

    private int takeIndex;

    private int putIndex;

    private int count;

    public BlockingQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity can not small than 0");
        items = new Object[capacity];
    }


    private void put(Object item) throws InterruptedException {
        synchronized (this) {
            while (count == items.length) {
                this.wait();
            }
            enqueue(item);
            this.notifyAll();

        }
    }


    private Object take() throws InterruptedException {
        synchronized (this) {
            while (count == 0) {
                this.wait();
            }
            Object e = dequeue();
            this.notifyAll();
            return e;
        }

    }

    private void enqueue(Object item) {
        items[putIndex] = item;
        if (++putIndex == items.length)
            putIndex = 0;
        count++;
    }

    private Object dequeue() {
        Object e = items[takeIndex];
        items[takeIndex] = null;

        if (++takeIndex == items.length)
            takeIndex = 0;
        count--;
        return e;
    }

}
