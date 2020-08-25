package me.valizadeh.challenges.airwallex.operator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class SubtractionTest {

    @Test
    public void testAddTwoNumber() {

        Subtraction subtraction = new Subtraction(new Value(BigDecimal.valueOf(10)), new Value(BigDecimal.valueOf(5)));

        BigDecimal returnValue = subtraction.execute();

        assertEquals("Result is not as expected!", BigDecimal.valueOf(5), returnValue);
    }
}
