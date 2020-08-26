package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A value {@link Statement} which contains a {@link BigDecimal} value.
 */
public class ValueStatement implements Statement {

    private final BigDecimal numberValue;

    public ValueStatement(BigDecimal numberValue) {
        this.numberValue = numberValue;
    }

    @Override
    public BigDecimal execute() {
        return numberValue;
    }

    @Override
    public List<Statement> unExecute() {
        return new ArrayList<>();
    }
}
