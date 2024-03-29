package com.tfr.collection.ringBuffer;

import java.util.ArrayList;
import java.util.List;

public class RingBuffer<T> {

    private final T[] buffer;
    private int currentSize;
    private int head;
    private int tail;

    /**
     * Create a ring buffer with max capacity of the provided size
     * @param size int
     */
    @SuppressWarnings("unchecked")
    public RingBuffer(int size) {
        this.buffer = (T[]) new Object[size];
        this.currentSize = 0;
        this.head = 0;
        this.tail = 0;
    }

    /**
     * Add an item to the ring buffer
     * @param item T
     */
    public void add(T item) {
        buffer[tail] = item;
        if (head == tail && currentSize > 0) {
            head = indexAfter(head);
        }
        tail = indexAfter(tail);
        conditionallyIncrementSize();
    }

    /**
     * Return the current size of the ring buffer
     * @return int
     */
    public int getSize() {
        return currentSize;
    }

    public T get(int index) {
        if (index > currentSize - 1) {
            throw new IndexOutOfBoundsException("Index " + index + " was out of bounds");
        }
        return buffer[indexAfter(head, index)];
    }

    /**
     * Remove the head item from the ring buffer
     * @return T
     */
    public T remove() {
        if (buffer[head] == null) {
            throw new UnderflowException("Tried to remove from empty buffer");
        }
        T item = buffer[head];
        buffer[head] = null;
        head = indexAfter(head);
        currentSize--;
        return item;
    }

    /**
     * Get all items from the ring buffer. Does not remove the items.
     * @return ArrayList<T>
     */
    public List<T> getAll() {
        List<T> items = new ArrayList<>();
        int count = 0;

        while(count < buffer.length) {
            T item = buffer[indexAfter(tail, count)];
            if (item != null) {
                items.add(item);
            }
            count++;
        }

        return items;
    }

    private int indexAfter(int index) {
        return indexAfter(index, 1);
    }

    private int indexAfter(int index, int incrementBy) {
        while (incrementBy > 0) {
            index = (index == buffer.length - 1) ? 0 : index + 1;
            incrementBy--;
        }
        return index;
    }

    private void conditionallyIncrementSize() {
        currentSize += (currentSize < buffer.length) ? 1 : 0;
    }


    /**
     * an exception thrown when an attempt is made to remove an item from an empty ring buffer
     */
    static class UnderflowException extends RuntimeException {
        UnderflowException(String message) {
            super(message);
        }
    }
}
