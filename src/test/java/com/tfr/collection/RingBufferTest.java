package com.tfr.collection;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RingBufferTest {

    @Test
    public void testNew_ExpectSizeZero() {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>(3);

        assertEquals(0, ringBuffer.getSize());
        assertEquals(List.of(), ringBuffer.getAll());
    }

    @Test
    public void testAdd_GivenOneItem_ExpectSizeOne() {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>(3);
        ringBuffer.add(1);
        assertEquals(1, ringBuffer.getSize());
        assertEquals(List.of(1), ringBuffer.getAll());
        assertEquals(1, ringBuffer.remove(), 0);
    }

    @Test
    public void testAdd_AddMoreThanSize_ExpectSize() {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>(3);
        ringBuffer.add(1);
        ringBuffer.add(2);
        ringBuffer.add(3);
        ringBuffer.add(4);
        assertEquals(3, ringBuffer.getSize());
        assertEquals(List.of(2,3,4), ringBuffer.getAll());
        assertEquals(2, ringBuffer.remove(), 0);
        assertEquals(3, ringBuffer.remove(), 0);
        assertEquals(4, ringBuffer.remove(), 0);
    }

    @Test (expected = RingBuffer.UnderflowException.class)
    public void testRemove_GivenEmpty_ExpectUnderflowException() {
        RingBuffer<Integer> ringBuffer = new RingBuffer<>(3);
        ringBuffer.remove();
    }
}
