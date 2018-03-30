package com.tfr.collection;

import java.util.ArrayList;
import java.util.List;

public class RingBuffer<T> {

    private T[] buffer;
    private int currentSize;
    private int nextIndex;
    private int head;

    @SuppressWarnings("unchecked")
    public RingBuffer(int size) {
        this.buffer = (T[]) new Object[size];
        this.currentSize = 0;
        this.nextIndex = 0;
        this.head = 0;
    }

    public void add(T item) {
        buffer[nextIndex] = item;
        if (head == nextIndex && currentSize > 0) {
            head = incrementIndex(head);
        }
        nextIndex = incrementIndex(nextIndex);
        currentSize += incrementSize();
    }

    public int getSize() {
        return this.currentSize;
    }

    public T remove() {
        if (buffer[head] == null) {
            throw new UnderflowException("Tried to remove from empty buffer");
        }
        T item = buffer[head];
        currentSize--;
        buffer[head] = null;
        head = incrementIndex(head);
        return item;
    }

    public List<T> getAll() {
        List<T> items = new ArrayList<>();
        int pointer = nextIndex;
        int count = 0;

        while(count < buffer.length) {
            if (buffer[pointer] != null) {
                items.add(buffer[pointer]);
            }
            pointer = (pointer == buffer.length - 1) ? 0 : pointer + 1;
            count++;
        }

        return items;
    }

    private int incrementIndex(int index) {
        return (index == buffer.length - 1) ? 0 : index + 1;
    }

    private int incrementSize() {
        return (currentSize < buffer.length) ? 1 : 0;
    }

    static class UnderflowException extends RuntimeException {
        UnderflowException(String message) {
            super(message);
        }
    }
}
