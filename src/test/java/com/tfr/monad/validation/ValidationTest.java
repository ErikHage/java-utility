package com.tfr.monad.validation;

import com.tfr.monad.model.FailedValidation;
import com.tfr.monad.model.Result;
import com.tfr.monad.model.SuccessfulValidation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidationTest {

    private Validation<Integer> validation;

    @Before
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
    }

    @Test
    public void testValidate_GivenInvalidInput_ExpectFailedValidation() {
        Result result = validation.validate(11);

        assertTrue(result instanceof FailedValidation);
    }

    @Test
    public void testVerify_GivenValidInput_ExpectNoException() {
        validation.verify(10);
    }

    @Test (expected = ValidationException.class)
    public void testVerify_GivenInvalidInput_ExpectException() {
        validation.verify(11);
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
