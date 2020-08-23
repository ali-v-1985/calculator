package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;

import java.math.BigDecimal;
import java.util.Deque;

public class Addition extends BinaryOperator {

    @Override
    public void calculate(Deque<BigDecimal> numbers, int pos) throws InsufficientParametersException {
        validate(numbers, pos);
        BigDecimal number2 = numbers.pop();
        BigDecimal number1 = numbers.pop();
        numbers.push(number1.add(number2));
    }

    @Override
    public String getName() {
        return OperatorFactory.ADDITION_SIGN;
    }
}
