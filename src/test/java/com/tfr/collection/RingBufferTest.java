package com.tfr.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RingBufferTest {

    private RingBuffer<Integer> rb;

    @Before
    public void setUp() {
        rb = new RingBuffer<>(3);
    }

    @Test
    public void testNew_ExpectSizeZero() {
        assertContents(List.of());
    }

    @Test
    public void testAdd_GivenOneItem_ExpectSizeOne() {
        addIncrementally(1);

        assertContents(List.of(1));
        assertEquals(1, rb.remove(), 0);
    }

    @Test
    public void testGet_GivenOneItem_ExpectSameReturned() {
        addIncrementally(1);

        assertContents(List.of(1));
        assertEquals(1, rb.get(0), 0);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGet_GivenEmpty_ExpectException() {
        assertContents(List.of());
        rb.get(0);
    }

    @Test
    public void testAdd_AddFourToSizeThree_ExpectLastThreeAdded() {
        addIncrementally(4);

        assertContents(List.of(2,3,4));
        assertEquals(2, rb.remove(), 0);
        assertEquals(3, rb.remove(), 0);
        assertEquals(4, rb.remove(), 0);
    }

    @Test (expected = RingBuffer.UnderflowException.class)
    public void testRemove_GivenEmpty_ExpectUnderflowException() {
        rb.remove();
    }

    private void assertContents(List<Integer> contents) {
        assertEquals(contents.size(), rb.getSize());
        assertEquals(contents, rb.getAll());
    }

    private void addIncrementally(int num) {
        for (int i=1; i<=num; i++) {
            rb.add(i);
        }
    }
}
