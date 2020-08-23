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
public class SubtractionTest {

    @InjectMocks
    private Subtraction subtraction;

    @Test
    public void testSubtractTwoNumber() throws InsufficientParametersException {

        Deque<BigDecimal> numbers = new ArrayDeque<>();
        numbers.push(BigDecimal.valueOf(10));
        numbers.push(BigDecimal.valueOf(5));

        subtraction.calculate(numbers, 5);

        assertNotNull("Result should not be null!", numbers);
        assertFalse("Result should not be empty!", numbers.isEmpty());
        assertEquals("Result is not as expected!", BigDecimal.valueOf(5), numbers.peek());
    }

    @Test
    public void testInsufficientParameters() {

        Deque<BigDecimal> numbers = new ArrayDeque<>();
        numbers.push(BigDecimal.valueOf(10));

        InsufficientParametersException assertThrows = Assertions.assertThrows(InsufficientParametersException.class,
                () -> subtraction.calculate(numbers, 5));


        assertEquals("Exception message should be as expected!",
                "Operator - (position: 5): insufficient parameters",
                assertThrows.getMessage());
    }
}
