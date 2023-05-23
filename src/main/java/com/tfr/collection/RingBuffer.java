package com.tfr.collection;

import java.util.ArrayList;
import java.util.List;

public class RingBuffer<T> {

    private final T[] buffer;
    private int currentSize;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public RingBuffer(int size) {
        this.buffer = (T[]) new Object[size];
        this.currentSize = 0;
        this.head = 0;
        this.tail = 0;
    }

    public void add(T item) {
        buffer[tail] = item;
        if (head == tail && currentSize > 0) {
            head = indexAfter(head);
        }
        tail = indexAfter(tail);
        conditionallyIncrementSize();
    }

    public int getSize() {
        return currentSize;
    }

    public T get(int index) {
        if (index > currentSize - 1) {
            throw new IndexOutOfBoundsException("Index " + index + " was out of bounds");
        }
        return buffer[indexAfter(head, index)];
    }

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

    static class UnderflowException extends RuntimeException {
        UnderflowException(String message) {
            super(message);
        }
    }
}
