package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;
import java.util.Deque;

public class Undo implements Operator {


    @Override
    public void calculate(Deque<BigDecimal> numbers, int pos) {
        numbers.pop();
    }

    /*@Override
    public Stack<BigDecimal> calculate(Stack<BigDecimal> numbers, final Stack<String> history)
            throws InsufficientParametersException, UnknownOperator {
        history.pop();
        Stack<BigDecimal> undidNumbers = new Stack<>();
        for (String token : history) {
            if (Utility.isNumeric(token)) {
                undidNumbers.push(new BigDecimal(token));
            } else {
                Operator operator = operatorFactory.get(token);
                undidNumbers = operator.calculate(undidNumbers);
            }
        }
        return undidNumbers;
    }*/

    @Override
    public int neededOperands() {
        return 0;
    }

    @Override
    public String getName() {
        return OperatorFactory.UNDO_SIGN;
    }
}
