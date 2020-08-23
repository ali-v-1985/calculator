package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;

public class Division extends BinaryOperator {

    @Override
    public void calculate(Deque<BigDecimal> numbers, int pos) throws InsufficientParametersException {
        validate(numbers, pos);
        BigDecimal number2 = numbers.pop();
        BigDecimal number1 = numbers.pop();
        numbers.push(number1.divide(number2, 15, RoundingMode.FLOOR));
    }

    @Override
    public String getName() {
        return OperatorFactory.DIVISION_SIGN;
    }
}
