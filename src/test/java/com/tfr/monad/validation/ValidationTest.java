package com.tfr.monad.validation;

import com.tfr.monad.model.FailedValidation;
import com.tfr.monad.model.Result;
import com.tfr.monad.model.SuccessfulValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {

    private Validation<Integer> validation;

    @BeforeEach
    public void setUp() {
        validation = new TestValidation();
    }

    @Test
    public void testGetName_ExpectClassName() {
        assertEquals(TestValidation.class.getName(), validation.getName());
    }

    @Test
    public void testValidate_GivenValidInput_ExpectSuccessfulValidation() {
        Result result = validation.validate(10);

        assertTrue(result instanceof SuccessfulValidation);
        assertTrue(result.isSuccessful());
        assertEquals("Validation 'com.tfr.monad.validation.ValidationTest$TestValidation' was successful", result.getMessage());
    }

    @Test
    public void testValidate_GivenInvalidInput_ExpectFailedValidation() {
        Result result = validation.validate(11);

        assertTrue(result instanceof FailedValidation);
        assertFalse(result.isSuccessful());
        assertEquals("Validation 'com.tfr.monad.validation.ValidationTest$TestValidation' failed with reason: Value did not equal 10", result.getMessage());
    }

    @Test
    public void testVerify_GivenValidInput_ExpectNoException() {
        validation.verify(10);
    }

    @Test
    public void testVerify_GivenInvalidInput_ExpectException() {
        assertThrows(ValidationException.class, () -> {
            validation.verify(11);
        });
    }

    @Test
    public void testValidationException() {
        assertThrows(ValidationException.class, () -> {
            throw new ValidationException("message", new Exception("caused by"));
        });
    }

    private static class TestValidation implements Validation<Integer> {
        @Override
        public void verify(Integer input) throws ValidationException {
            if (input != 10) {
                throw new ValidationException("Value did not equal 10");
            }
        }
    }

}
