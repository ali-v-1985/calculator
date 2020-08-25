package me.valizadeh.challenges.airwallex.operation;

import java.math.BigDecimal;
import java.util.Deque;

public abstract class Root extends UnaryOperation {

    private final double rootPowBase;

    public Root(Operation operand, int rootBase) {
        super(operand);
        this.rootPowBase = (1.0 / rootBase);
    }

    @Override
    public BigDecimal execute() {

        double rootValue = Math.pow(operand.execute().doubleValue(), this.rootPowBase);

        return BigDecimal.valueOf(rootValue);
    }
}
