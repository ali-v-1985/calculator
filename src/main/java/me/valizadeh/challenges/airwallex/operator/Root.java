package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;

import java.math.BigDecimal;
import java.util.Stack;

public abstract class Root extends UnaryOperator {

    protected int rootValue;

    @Override
    public Stack<BigDecimal> calculate(Stack<BigDecimal> numbers, int pos) throws InsufficientParametersException {
        validate(numbers, pos);
        BigDecimal number1 = numbers.pop();
        numbers.push(number1);//TODO:root it
        return numbers;
    }

}
