package com.tfr.lazy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LazyTest {

    private final String testString = "Test String";
    private AutoCloseable closeable;
    @Mock
    private Supplier<String> stringSupplier;

    @BeforeEach
    public void beforeEach() {
        closeable = MockitoAnnotations.openMocks(this);
        when(stringSupplier.get()).thenReturn(testString);
    }

    @AfterEach
    public void cleanUp() throws Exception {
        closeable.close();
    }

    @Test
    public void testGetValue_ExpectValueNotLoadedOnInstantiation() {
        new Lazy<>(stringSupplier);

        verify(stringSupplier, times(0)).get();
    }

    @Test
    public void testGetValue_ExpectLoadValueOnFirstCall() {
        Lazy<String> lazyString = new Lazy<>(stringSupplier);

        verify(stringSupplier, times(0)).get();

        String result = lazyString.getValue();

        assertEquals(testString, result);
        verify(stringSupplier, times(1)).get();
    }

    @Test
    public void testGetValue_ExpectNotLoadValueOnSubsequentCalls() {
        Lazy<String> lazyString = new Lazy<>(stringSupplier);

        verify(stringSupplier, times(0)).get();
        lazyString.getValue();
        verify(stringSupplier, times(1)).get();
        lazyString.getValue();
        verify(stringSupplier, times(1)).get();
    }
}
