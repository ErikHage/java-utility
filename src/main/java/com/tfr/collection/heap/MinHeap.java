package com.tfr.collection.heap;

import com.tfr.collection.exception.OverflowException;

/**
 *  A Min-Heap is a complete binary tree in which the value in each internal node is smaller than or equal to the
 *  values in the children of that node. Mapping the elements of a heap into an array is trivial: if a node is stored
 *  an index k, then its left child is stored at index 2k + 1 and its right child at index 2k + 2.
 * @param <T> extends Comparable<T>
 */
public class MinHeap<T extends Comparable<T>> implements Heap<T> {

    private static final int FRONT = 1;

    private final Object[] heap;
    private final int maxSize;
    private int size;

    public MinHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;

        heap = new Object[this.maxSize + 1];
        heap[0] = null;
    }

    /**
     * Returns the root element of the MinHeap. Time complexity is O(1).
     * @return T
     */
    public T peek() {
        return (T) heap[FRONT];
    }

    /**
     * Removes the minimum element from the MinHeap. Time complexity is O(log n) as this operation needs to maintain
     * the heap property after removing the root.
     * @return T
     */
    public T remove() {
        if (size <= 0) {
            return null;
        }

        T popped = (T) heap[FRONT];
        heap[FRONT] = heap[size--];
        minHeapify(FRONT);

        return popped;
    }

    /**
     * Inserting a new item takes O(log n) time. The new item is added at the end of the tree. If a new item is larger
     * than its parent, nothing needs to be done. Otherwise, the tree must be traversed to fix up the violated heap
     * property.
     * @param item T
     */
    public void insert(T item) {
        if (size >= maxSize) {
            throw new OverflowException("MinHeap is full");
        }

        if (item == null) {
            return;
        }

        heap[++size] = item;
        int current = size;

        while (compareItems(current, parent(current)) < 0) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    /**
     * Print the contents of the MinHeap
     */
    public void print() {
        System.out.println("Heap:");
        for (int i = 0; i <= size; i++) {
            System.out.println(heap[i]);
        }

        System.out.println("-----");

        for (int i = 1; i <= size / 2; i++) {
            System.out.println(
                    " PARENT: " + heap[i]
                    + " LEFT CHILD: " + heap[2 * i]
                    + " RIGHT CHILD: " + heap[2 * i + 1]
            );
            System.out.println();
        }
    }

    /**
     * Get current size of the MinHeap
     * @return int
     */
    public int size() {
        return size;
    }

    /**
     * Return the position of the parent for the node at the given position
     * @param position int
     * @return int
     */
    private int parent(int position) {
        return (position / 2);
    }

    /**
     * Return the position of the left child for the node at the given position
     * @param position int
     * @return int
     */
    private int leftChild(int position) {
        return (2 * position);
    }

    /**
     * Return the position of the right child for the node at the given position
     * @param position int
     * @return int
     */
    private int rightChild(int position) {
        return (2 * position) + 1;
    }

    /**
     * Return true if the node at the given position is a leaf node
     * @param position int
     * @return boolean
     */
    private boolean isLeaf(int position) {
        return position > (size / 2);
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
     * Heapify the node at given position
     * @param position int
     */
    private void minHeapify(int position) {
        if (!isLeaf(position)) {
            int swapPosition = position;
            int rightChildPos = rightChild(position);
            int leftChildPos = leftChild(position);

            // swap with the minimum of the two children
            if (rightChild(position) <= size) {
                swapPosition = compareItems(leftChildPos, rightChildPos) < 0 ? leftChildPos : rightChildPos;
            } else {
                swapPosition = leftChildPos;
            }

            if (compareItems(position, leftChildPos) > 0 || compareItems(position, rightChildPos) > 0) {
                swap(position, swapPosition);
                minHeapify(swapPosition);
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
        if (firstPosition == 0 || secondPosition == 0) {
            return 0;
        }

        T first = (T) heap[firstPosition];
        T second = (T) heap[secondPosition];

        return first.compareTo(second);
    }

}

