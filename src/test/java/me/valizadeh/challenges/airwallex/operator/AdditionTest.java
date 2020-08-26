package me.valizadeh.challenges.airwallex.operator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class AdditionTest {

    @Test
    public void testAddTwoNumber() {

        Addition addition = new Addition(new ValueStatement(BigDecimal.valueOf(10)), new ValueStatement(BigDecimal.valueOf(5)));

        BigDecimal returnValue = addition.execute();

        assertEquals("Result is not as expected!", BigDecimal.valueOf(15), returnValue);
    }
}
