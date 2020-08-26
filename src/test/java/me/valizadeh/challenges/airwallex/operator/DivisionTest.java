package me.valizadeh.challenges.airwallex.operator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class DivisionTest {

    @Test
    public void testAddTwoNumber() {

        Division division = new Division(new ValueStatement(BigDecimal.valueOf(10)), new ValueStatement(BigDecimal.valueOf(5)));

        BigDecimal returnValue = division.execute();

        assertEquals("Result is not as expected!", 2, returnValue.intValue());
    }

    @Test
    public void testDivideTwoNumberWithPrecision() {

        Division division = new Division(new ValueStatement(BigDecimal.valueOf(10)), new ValueStatement(BigDecimal.valueOf(3)));

        BigDecimal returnValue = division.execute();
        assertEquals("Result is not as expected!", BigDecimal.valueOf(3.333333333333333), returnValue);
    }
}
