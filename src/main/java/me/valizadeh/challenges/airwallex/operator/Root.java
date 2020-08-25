package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;

public abstract class Root extends UnaryOperator {

    private final double rootPowBase;

    public Root(Operator operand, int rootBase) {
        super(operand);
        this.rootPowBase = (1.0 / rootBase);
    }

    @Override
    public BigDecimal execute() {

        double rootValue = Math.pow(operand.execute().doubleValue(), this.rootPowBase);

        return BigDecimal.valueOf(rootValue);
    }
}
