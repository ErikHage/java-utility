package com.tfr.collection.heap;

import com.tfr.collection.exception.OverflowException;

/**
 * A max-heap is a complete binary tree in which the value in each internal node is greater than or equal to the values
 * in the children of that node. Mapping the elements of a heap into an array is trivial: if a node is stored an index
 * k, then its left child is stored at index 2k + 1 and its right child at index 2k + 2.
 * @param <T> extends Comparable<T>
 */
public class MaxHeap<T extends Comparable<T>> implements Heap<T> {
    private final Object[] heap;
    private final int maxSize;
    private int size;

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;

        heap = new Object[this.maxSize];
    }

    /**
     * Returns the root element of the MaxHeap. Time complexity is O(1).
     * @return T
     */
    @Override
    public T peek() {
        return (T) heap[0];
    }

    /**
     * Removes the maximum element from the MaxHeap. Time complexity is O(log n) as this operation needs to maintain
     * the heap property after removing the root.
     * @return T
     */
    @Override
    public T remove() {
        if (size <= 0) {
            return null;
        }

        T popped = (T) heap[0];

        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        maxHeapify(0);

        return popped;
    }

    /**
     * Inserting a new item takes O(log n) time. The new item is added at the end of the tree. If a new item is smaller
     * than its parent, nothing needs to be done. Otherwise, the tree must be traversed to fix up the violated heap
     * property.
     * @param item T
     */
    @Override
    public void insert(T item) {
        if (size >= maxSize) {
            throw new OverflowException("MaxHeap is full");
        }

        if (item == null) {
            return;
        }

        heap[size] = item;

        int current = size;
        while (compareItems(current, parent(current)) > 0) {
            swap(current, parent(current));
            current = parent(current);
        }
        size++;
    }

    /**
     * Get current size of the MinHeap
     * @return int
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Print the contents of the MaxHeap
     */
    @Override
    public void print() {
        System.out.println("Heap:");
        for (int i = 0; i < size; i++) {
            System.out.println(heap[i]);
        }

        for (int i = 0; i < size / 2; i++) {
            System.out.print("Parent Node : " + heap[i]);

            if (leftChild(i) < size) {
                System.out.print(" Left Child Node: " + heap[leftChild(i)]);
            }
            if (rightChild(i) < size) {
                System.out.print(" Right Child Node: " + heap[rightChild(i)]);
            }

            System.out.println();
        }
    }

    /**
     * Return the position of the parent for the node at the given position
     * @param position int
     * @return int
     */
    private int parent(int position) {
        return (position - 1) / 2;
    }

    /**
     * Return the position of the left child for the node at the given position
     * @param position int
     * @return int
     */
    private int leftChild(int position) {
        return (2 * position) + 1;
    }

    /**
     * Return the position of the right child for the node at the given position
     * @param position int
     * @return int
     */
    private int rightChild(int position) {
        return (2 * position) + 2;
    }

    /**
     * Return true if the node at the given position is a leaf node
     * @param position int
     * @return boolean
     */
    private boolean isLeaf(int position) {
        return position > (size / 2) && position <= size;
    }

    /**
     * Swap two nodes in the MinHeap
     * @param firstPosition int
     * @param secondPosition int
     */
    private void swap(int firstPosition, int secondPosition) {
        T temp;

        temp = (T) heap[firstPosition];

        heap[firstPosition] = heap[secondPosition];
        heap[secondPosition] = temp;
    }

    /**
     * Recursive function to max heapify given subtree
     * @param position int
     */
    private void maxHeapify(int position) {
        if (isLeaf(position)) {
            return;
        }

        int rightChildPos = rightChild(position);
        int leftChildPos = leftChild(position);

        if (compareItems(position, leftChildPos) < 0 || compareItems(position, rightChildPos) < 0) {
            if (compareItems(leftChildPos, rightChildPos) > 0) {
                swap(position, leftChildPos);
                maxHeapify(leftChildPos);
            } else {
                swap(position, rightChildPos);
                maxHeapify(rightChildPos);
            }
        }
    }

    /**
     * Compare items at two positions, returning result of the compareTo function.
     * @param firstPosition int
     * @param secondPosition int
     * @return boolean
     */
    private int compareItems(int firstPosition, int secondPosition) {
        T first = (T) heap[firstPosition];
        T second = (T) heap[secondPosition];

        if (first == null) {
            return 1;
        }
        if (second == null) {
            return 1;
        }

        return first.compareTo(second);
    }
}
