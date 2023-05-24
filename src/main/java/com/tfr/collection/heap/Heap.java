package com.tfr.collection.heap;

/**
 * Interface for a Heap collection
 * @param <T> extends Comparable<T>
 */
public interface Heap<T extends Comparable<T>> {
    T peek();
    T remove();
    void insert(T item);
    int size();
    void print();
}
