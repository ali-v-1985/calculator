package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.utils.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A abstract class which provides the root {@link Statement} based on the {@link this#rootPowBase}.
 */
public abstract class Root extends UnaryStatement {

    private final double rootPowBase;


    /**
     * @param operand the operand which this root {@link Statement} should be applied on.
     * @param rootBase the rootBase which this root {@link Statement} should work based on.
     */
    public Root(Statement operand, int rootBase) {
        super(operand);
        this.rootPowBase = (1.0 / rootBase);
    }

    /**
     * Executes the root on the operand.
     *
     * @return a big decimal result of root operation.
     */
    @Override
    public BigDecimal execute() {
        double rootValue = Math.pow(operand.execute().doubleValue(), this.rootPowBase);
        return BigDecimal.valueOf(rootValue).setScale(Constants.SCALE, RoundingMode.FLOOR);
    }
}
