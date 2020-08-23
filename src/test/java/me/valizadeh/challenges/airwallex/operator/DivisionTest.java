package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Stack;

import static org.springframework.test.util.AssertionErrors.*;

@ExtendWith(MockitoExtension.class)
public class DivisionTest {

    @InjectMocks
    private Division division;

    @Test
    public void testDivideTwoNumber() throws InsufficientParametersException {

        Stack<BigDecimal> numbers = new Stack<>();
        numbers.push(BigDecimal.valueOf(10));
        numbers.push(BigDecimal.valueOf(5));

        Stack<BigDecimal> calculate = division.calculate(numbers, 5);

        assertNotNull("Result should not be null!", calculate);
        assertFalse("Result should not be empty!", calculate.empty());
        assertTrue("Result should not as expected!", calculate.pop().equals(BigDecimal.valueOf(2)));
    }

    @Test
    public void testInsufficientParameters() {

        Stack<BigDecimal> numbers = new Stack<>();
        numbers.push(BigDecimal.valueOf(10));

        InsufficientParametersException assertThrows = Assertions.assertThrows(InsufficientParametersException.class,
                () -> division.calculate(numbers, 5));


        assertEquals("Exception message should be as expected!",
                "Operator / (position: 5): insufficient parameters",
                assertThrows.getMessage());
    }
}
