package me.valizadeh.challenges.airwallex.operator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ClearTest {

    @InjectMocks
    private Clear clear;

    @Test
    public void testClear() {

        Deque<BigDecimal> numbers = new ArrayDeque<>();
        numbers.push(BigDecimal.valueOf(10));
        numbers.push(BigDecimal.valueOf(5));

        clear.calculate(numbers, 5);

        assertNotNull("Result should not be null!", numbers);
        assertTrue("Result should be empty!", numbers.isEmpty());
    }
}
