package com.tfr.monad;

import com.tfr.monad.validation.ValidationException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StateTest {

    private State<Integer> state;

    @Before
    public void setUp() {
        state = State.of(10);
    }

    @Test
    public void testGetValue_ExpectValue() {
        assertEquals(10, state.getValue(), 0);
    }

    @Test
    public void testValidate_ExpectSuccess() {
        state.validate(v -> doThrow(false));
    }

    @Test
    public void testValidate_GivenMultipleValidations_ExpectSuccess() {
        state.validate(v -> doThrow(false))
                .validate(v -> doThrow(false));
    }

    @Test (expected = ValidationException.class)
    public void testValidate_ExpectFailure() {
        state.validate(v -> doThrow(true));
    }

    @Test
    public void testMap_GivenTransformation_ExpectTransformedValue() {
        State<String> result = state.map(Object::toString);
        assertEquals("10", result.getValue());
    }

    @Test
    public void testFlatMap_GivenTransformationReturnsWrappedValue_ExpectTransformedValue() {
        State<String> result = state.flatMap(v -> State.of(v.toString()));
        assertEquals("10", result.getValue());
    }

    private void doThrow(boolean doThrow) {
        if (doThrow) {
            throw new ValidationException("A message");
        }
    }


}
