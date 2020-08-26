package me.valizadeh.challenges.airwallex.operator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class SquareRootTest {

    @Test
    public void testSquareRoot() {

        Root root = new SquareRoot(new ValueStatement(BigDecimal.valueOf(9)));

        BigDecimal returnValue = root.execute();

        assertEquals("Result is not as expected!", 3, returnValue.intValue());
    }

    @Test
    public void testSquareRootWithPrecision() {

        Root root = new SquareRoot(new ValueStatement(BigDecimal.valueOf(2)));

        BigDecimal returnValue = root.execute();

        assertEquals("Result is not as expected!", BigDecimal.valueOf(1.414213562373095), returnValue);
    }
}
