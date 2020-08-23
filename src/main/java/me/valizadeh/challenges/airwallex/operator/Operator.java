package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import me.valizadeh.challenges.airwallex.exception.UnknownOperator;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Stack;

public interface Operator {


    String INSUFFICIENT_PARAMETERS_WARN = "operator {0} (position: {1}): insufficient parameters";

    Stack<BigDecimal> calculate(Stack<BigDecimal> numbers, int pos) throws InsufficientParametersException;

    default void validate(Stack<BigDecimal> numbers, int pos)  throws InsufficientParametersException {
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
