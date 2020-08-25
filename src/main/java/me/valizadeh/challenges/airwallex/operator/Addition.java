package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;

/**
 * The {@link Addition} {@link Statement} which is a {@link BinaryStatement}
 */
public class Addition extends BinaryStatement {

    /**
     *
     * @param operand1 first operand of the addition {@link Statement}
     * @param operand2 second operand of the addition {@link Statement}
     */
    public Addition(Statement operand1, Statement operand2) {
        super(operand1, operand2);
    }

    /**
     * Executes the addition on the operands.
     *
     * @return a big decimal result of addition operation.
     */
    @Override
    public BigDecimal execute() {
        return operand1.execute().add(operand2.execute());
    }
}
