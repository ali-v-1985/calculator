package me.valizadeh.challenges.airwallex.operator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class MultiplicationTest {

    @Test
    public void testAddTwoNumber() {

        Multiplication multiplication = new Multiplication(new ValueStatement(BigDecimal.valueOf(10)), new ValueStatement(BigDecimal.valueOf(5)));

        BigDecimal returnValue = multiplication.execute();

        assertEquals("Result is not as expected!", BigDecimal.valueOf(50), returnValue);
    }
}
