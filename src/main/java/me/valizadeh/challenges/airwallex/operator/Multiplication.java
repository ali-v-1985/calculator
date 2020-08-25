package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;

/**
 * The {@link Multiplication} {@link Statement} which is a {@link BinaryStatement}
 */
public class Multiplication extends BinaryStatement {

    /**
     *
     * @param operand1 first operand of the multiplication {@link Statement}
     * @param operand2 second operand of the multiplication {@link Statement}
     */
    public Multiplication(Statement operand1, Statement operand2) {
        super(operand1, operand2);
    }

    /**
     * Executes the multiplication on the operands.
     *
     * @return a big decimal result of multiplication operation.
     */
    @Override
    public BigDecimal execute() {
        return operand1.execute().multiply(operand2.execute());
    }
}
