package com.tfr.monad;

import com.tfr.monad.model.FailedValidation;
import com.tfr.monad.validation.ValidationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WriterTest {

    private Writer<Integer> writer;

    @Before
    public void setUp() {
        writer = Writer.of(10);
    }

    @Test
    public void testGetValue_ExpectValue() {
        assertEquals(10, writer.getValue(), 0);
        assertEquals(0, writer.getLog().size());
    }

    @Test
    public void testValidate_ExpectOneLoggedMessage() {
        writer.validate(v -> doThrow(false));

        assertEquals(1, writer.getLog().size());
    }

    @Test
    public void testValidate_GivenMultipleValidations_ExpectMultipleLoggedMessages() {
        writer.validate(v -> doThrow(false))
                .validate(v -> doThrow(false));

        assertEquals(2, writer.getLog().size());
    }

    @Test
    public void testValidate_ExpectFailureLogged() {
        writer.validate(v -> doThrow(true));

        assertEquals(1, writer.getLog().size());
        assertTrue(writer.getLog().get(0) instanceof FailedValidation);
    }

    @Test
    public void testMap_GivenTransformation_ExpectTransformedValue() {
        Writer<String> result = writer.map(Object::toString);
        assertEquals("10", result.getValue());
    }

    @Test
    public void testFlatMap_GivenTransformationReturnsWrappedValue_ExpectTransformedValue() {
        Writer<String> result = writer.flatMap(v -> Writer.of(v.toString()));
        assertEquals("10", result.getValue());
    }

    private void doThrow(boolean doThrow) {
        if (doThrow) {
            throw new ValidationException("A message");
        }
    }
}
