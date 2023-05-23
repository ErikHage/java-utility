package com.tfr.collection.heap;

import com.tfr.collection.exception.OverflowException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {

    @Test
    public void testPrint_ExpectSuccess() {
        MinHeap<String> heap = new MinHeap<>(5);

        heap.insert("a");
        heap.insert("b");
        heap.insert("c");
        heap.insert("d");

        heap.print();
    }

    @Test
    public void testInsert_GivenFullMinHeap_ExpectOverflowException() {
        MinHeap<String> heap = new MinHeap<>(2);

        heap.insert("a");
        heap.insert("b");
        assertThrows(OverflowException.class, () -> {
            heap.insert("c");
        });
    }

    @Test
    public void testInsertAndPeek_ExpectMinValue() {
        MinHeap<String> heap = new MinHeap<>(5);

        heap.insert("a");
        heap.insert("b");
        heap.insert("c");
        heap.insert("d");

        assertEquals("a", heap.peek());
    }

    @Test
    public void testInsertAndPeek_ExpectNullNotAdded() {
        MinHeap<String> heap = new MinHeap<>(5);

        heap.insert("a");
        heap.insert("b");
        heap.insert(null);
        heap.insert("d");

        assertNotNull(heap.remove());
        assertNotNull(heap.remove());
        assertNotNull(heap.remove());
        assertEquals(0, heap.size());
    }

    @Test
    public void testInsertAndRemove_ExpectDuplicatesAllowed() {
        MinHeap<String> heap = new MinHeap<>(5);

        heap.insert("a");
        heap.insert("c");
        heap.insert("b");
        heap.insert("a");

        assertEquals("a", heap.remove());
        assertEquals("a", heap.remove());
        assertEquals("b", heap.remove());
        assertEquals("c", heap.remove());
    }

    @Test
    public void testRemove_ExpectRemoveInOrder() {
        MinHeap<String> heap = new MinHeap<>(5);

        heap.insert("a");
        heap.insert("b");
        heap.insert("c");
        heap.insert("d");

        assertEquals("a", heap.remove());
        assertEquals("b", heap.remove());
        assertEquals("c", heap.remove());
        assertEquals("d", heap.remove());
    }

    @Test
    public void testRemove_GivenEmptyHeap_ExpectNull() {
        MinHeap<String> heap = new MinHeap<>(5);

        assertNull(heap.remove());
    }

    @Test
    public void testPeek_GivenEmptyHeap_ExpectNull() {
        MinHeap<String> heap = new MinHeap<>(5);

        assertNull(heap.peek());
    }

}
