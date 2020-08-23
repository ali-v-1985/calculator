package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;

import java.math.BigDecimal;
import java.util.Stack;

public class Multiplication extends BinaryOperator {

    @Override
    public Stack<BigDecimal> calculate(Stack<BigDecimal> numbers, int pos) throws InsufficientParametersException {
        validate(numbers, pos);
        BigDecimal number2 = numbers.pop();
        BigDecimal number1 = numbers.pop();
        numbers.push(number1.multiply(number2));
        return numbers;
    }

    @Override
    public String getName() {
        return OperatorFactory.MULTIPLICATION;
    }
}
