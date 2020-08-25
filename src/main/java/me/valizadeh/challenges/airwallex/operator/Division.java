package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The {@link Division} {@link Statement} which is a {@link BinaryStatement}
 */
public class Division extends BinaryStatement {

    /**
     *
     * @param operand1 first operand of the division {@link Statement}
     * @param operand2 second operand of the division {@link Statement}
     */
    public Division(Statement operand1, Statement operand2) {
        super(operand1, operand2);
    }

    /**
     * Executes the division on the operands.
     *
     * @return a big decimal result of division operation.
     */
    @Override
    public BigDecimal execute() {
        return operand1.execute().divide(operand2.execute(), 15, RoundingMode.FLOOR);
    }
}
