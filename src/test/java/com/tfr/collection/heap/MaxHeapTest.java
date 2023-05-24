package com.tfr.collection.heap;

import com.tfr.collection.exception.OverflowException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MaxHeapTest {

    @Test
    public void testPrint_ExpectSuccess() {
        MaxHeap<String> heap = new MaxHeap<>(5);

        heap.insert("a");
        heap.insert("b");
        heap.insert("c");
        heap.insert("d");

        heap.print();
    }

    @Test
    public void testInsert_GivenFullHeap_ExpectOverflowException() {
        MaxHeap<String> heap = new MaxHeap<>(2);

        heap.insert("a");
        heap.insert("b");
        assertThrows(OverflowException.class, () -> {
            heap.insert("c");
        });
    }

    @Test
    public void testInsertAndPeek_ExpectMaxValue() {
        MaxHeap<String> heap = new MaxHeap<>(5);

        heap.insert("a");
        heap.insert("d");
        heap.insert("c");
        heap.insert("b");

        assertEquals("d", heap.peek());
    }

    @Test
    public void testInsertAndRemove_ExpectNullNotAdded() {
        MaxHeap<String> heap = new MaxHeap<>(5);

        heap.insert("a");
        heap.insert("b");
        heap.insert(null);
        heap.insert("d");

        heap.print();

        assertNotNull(heap.remove());
        assertNotNull(heap.remove());
        assertNotNull(heap.remove());
        assertEquals(0, heap.size());
    }

    @Test
    public void testInsertAndRemove_ExpectDuplicatesAllowed() {
        MaxHeap<String> heap = new MaxHeap<>(5);

        heap.insert("a");
        heap.insert("c");
        heap.insert("b");
        heap.insert("a");

        assertEquals("c", heap.remove());
        assertEquals("b", heap.remove());
        assertEquals("a", heap.remove());
        assertEquals("a", heap.remove());
    }

    @Test
    public void testRemove_ExpectRemoveInOrder() {
        MaxHeap<String> heap = new MaxHeap<>(5);

        heap.insert("b");
        heap.insert("a");
        heap.insert("d");
        heap.insert("c");

        heap.print();

        assertEquals("d", heap.remove());
        assertEquals("c", heap.remove());
        assertEquals("b", heap.remove());
        assertEquals("a", heap.remove());
    }

    @Test
    public void testRemove_GivenEmptyHeap_ExpectNull() {
        MaxHeap<String> heap = new MaxHeap<>(5);

        assertNull(heap.remove());
    }

    @Test
    public void testPeek_GivenEmptyHeap_ExpectNull() {
        MaxHeap<String> heap = new MaxHeap<>(5);

        assertNull(heap.peek());
    }
}
