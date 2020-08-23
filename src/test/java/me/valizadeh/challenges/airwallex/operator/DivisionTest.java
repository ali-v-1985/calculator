package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

import static org.springframework.test.util.AssertionErrors.*;

@ExtendWith(MockitoExtension.class)
public class DivisionTest {

    @InjectMocks
    private Division division;

    @Test
    public void testDivideTwoNumber() throws InsufficientParametersException {

        Deque<BigDecimal> numbers = new ArrayDeque<>();
        numbers.push(BigDecimal.valueOf(10));
        numbers.push(BigDecimal.valueOf(5));

        division.calculate(numbers, 5);

        assertNotNull("Result should not be null!", numbers);
        assertFalse("Result should not be empty!", numbers.isEmpty());
        assertNotNull("Result expected to have one element!", numbers.peek());
        if (numbers.peek() != null) {
            assertEquals("Result is not as expected!", 2, numbers.peek().intValue());
        }
    }

    @Test
    public void testDivideTwoNumberWithPrecision() throws InsufficientParametersException {

        Deque<BigDecimal> numbers = new ArrayDeque<>();
        numbers.push(BigDecimal.valueOf(10));
        numbers.push(BigDecimal.valueOf(3));

        division.calculate(numbers, 5);

        assertNotNull("Result should not be null!", numbers);
        assertFalse("Result should not be empty!", numbers.isEmpty());
        assertEquals("Result is not as expected!", BigDecimal.valueOf(3.333333333333333), numbers.peek());
    }

    @Test
    public void testInsufficientParameters() {

        Deque<BigDecimal> numbers = new ArrayDeque<>();
        numbers.push(BigDecimal.valueOf(10));

        InsufficientParametersException assertThrows = Assertions.assertThrows(InsufficientParametersException.class,
                () -> division.calculate(numbers, 5));


        assertEquals("Exception message should be as expected!",
                "Operator / (position: 5): insufficient parameters",
                assertThrows.getMessage());
    }
}
