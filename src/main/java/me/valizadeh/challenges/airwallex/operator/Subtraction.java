package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;

/**
 * The {@link Subtraction} {@link Statement} which is a {@link BinaryStatement}
 */
public class Subtraction extends BinaryStatement {

    /**
     *
     * @param operand1 first operand of the subtraction {@link Statement}
     * @param operand2 second operand of the subtraction {@link Statement}
     */
    public Subtraction(Statement operand1, Statement operand2) {
        super(operand1, operand2);
    }

    /**
     * Executes the subtraction on the operands.
     *
     * @return a big decimal result of subtraction operation.
     */
    @Override
    public BigDecimal execute() {
        return operand1.execute().subtract(operand2.execute());
    }
}
