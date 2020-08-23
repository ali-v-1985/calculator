package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Deque;

import static me.valizadeh.challenges.airwallex.utils.MessageUtil.INSUFFICIENT_PARAMETERS_WARN;

public interface Operator {


    void calculate(Deque<BigDecimal> numbers, int pos) throws InsufficientParametersException;

    default void validate(Deque<BigDecimal> numbers, int pos) throws InsufficientParametersException {
        if (numbers.size() < neededOperands()) {
            throw new InsufficientParametersException(MessageFormat.format(INSUFFICIENT_PARAMETERS_WARN,
                    getName(),
                    pos));
        }
    }

    /*default Stack<BigDecimal> calculate(Stack<BigDecimal> numbers, Stack<String> history)
            throws InsufficientParametersException, UnknownOperator {
        return calculate(numbers);
    }*/

    int neededOperands();

    default String getName() {
        return this.getClass().getSimpleName();
    }

}
