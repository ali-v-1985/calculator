package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;
import java.util.Deque;

public class Clear implements Operator {

    @Override
    public void calculate(Deque<BigDecimal> numbers, int pos) {
        numbers.clear();
    }

    @Override
    public int neededOperands() {
        return 0;
    }

    @Override
    public String getName() {
        return OperatorFactory.CLEAR_SIGN;
    }
}
