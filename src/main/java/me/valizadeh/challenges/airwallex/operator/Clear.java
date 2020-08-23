package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;
import java.util.Stack;

public class Clear implements Operator {

    @Override
    public Stack<BigDecimal> calculate(Stack<BigDecimal> numbers, int pos) {
        numbers.clear();
        return numbers;
    }

    @Override
    public int neededOperands() {
        return 0;
    }

    @Override
    public String getName() {
        return OperatorFactory.CLEAR;
    }
}
