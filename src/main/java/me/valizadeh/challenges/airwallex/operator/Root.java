package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;

import java.math.BigDecimal;
import java.util.Deque;

public abstract class Root extends UnaryOperator {

    private final double rootPowBase;

    public Root(int rootBase) {
        this.rootPowBase = (1.0 / rootBase);
    }

    @Override
    public void calculate(Deque<BigDecimal> numbers, int pos) throws InsufficientParametersException {
        validate(numbers, pos);
        BigDecimal number1 = numbers.pop();

        double rootValue = Math.pow(number1.doubleValue(), this.rootPowBase);

        numbers.push(BigDecimal.valueOf(rootValue));
    }

}
